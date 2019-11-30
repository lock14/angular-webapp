package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.domain.ApiConvertibleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.util.MultiValueMap;

import java.util.Optional;
import java.util.function.Function;

public abstract class ApiConverterService<Repo extends PagingAndSortingRepository<Entity, Id> & JpaSpecificationExecutor<Entity>,
        ApiResource, Entity extends ApiConvertibleEntity<Entity, ApiResource>, Id>
        implements PagingRestService<ApiResource, Id> {

    private Repo repository;
    private Function<ApiResource, Entity> fromApi;

    ApiConverterService(Repo repository, Function<ApiResource, Entity> fromApi) {
        this.repository = repository;
        this.fromApi = fromApi;
    }

    @Override
    public Page<ApiResource> findAll(MultiValueMap<String, String> filters, Pageable pageable) {
        return repository.findAll(toSpecification(filters), pageable)
                         .map(Entity::toApi);
    }

    @Override
    public Optional<ApiResource> findById(Id id) {
        return repository.findById(id)
                         .map(Entity::toApi);
    }

    @Override
    public ApiResource save(ApiResource apiResource) {
        return repository.save(fromApi.apply(apiResource))
                         .toApi();
    }

    @Override
    public void deleteById(Id id) {
        repository.deleteById(id);
    }

    public Specification<Entity> toSpecification(MultiValueMap<String, String> filters) {
        return null;
    }
}
