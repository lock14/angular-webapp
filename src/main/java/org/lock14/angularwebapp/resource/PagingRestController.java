package org.lock14.angularwebapp.resource;

import org.lock14.angularwebapp.api.ApiCopyable;
import org.lock14.angularwebapp.api.ApiPage;
import org.lock14.angularwebapp.service.PagingRestService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

public class PagingRestController<T extends ApiCopyable<T>, ID> {
    private PagingRestService<T, ID> restService;

    public PagingRestController(PagingRestService<T, ID> restService) {
        this.restService = restService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiPage<T>> findAll(
            @RequestParam MultiValueMap<String, String> queryParams,
            Pageable pageable) {
        return ResponseEntity.ok(ApiPage.of(restService.findAll(queryParams, pageable)));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> findById(@PathVariable ID id) {
        return restService.findById(id)
                          .map(ResponseEntity::ok)
                          .orElseThrow(PagingRestController::notFoundException);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> create(T resource) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.restService.save(resource));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T resource) {
        return restService.findById(id)
                          .map(existing -> existing.copy(resource))
                          .map(restService::save)
                          .map(ResponseEntity::ok)
                          .orElseThrow(PagingRestController::notFoundException);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable ID id) {
        restService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private static ResponseStatusException notFoundException() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "No Such Resource");
    }
}
