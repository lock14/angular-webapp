package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.mapper.ApiMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

public abstract class ApiMappingService<Repo extends CrudRepository<Entity, Id> & PagingAndSortingRepository<Entity, Id> & JpaSpecificationExecutor<Entity>,
        ApiResource, Entity, Id> implements PagingRestService<ApiResource, Id> {

    private Repo repository;
    private ApiMapper<ApiResource, Entity> mapper;
    private SpecificationGenerator<Entity> specificationGenerator;

    ApiMappingService(Repo repository, ApiMapper<ApiResource, Entity> mapper,
                      SpecificationGenerator<Entity> specificationGenerator) {
        this.repository = repository;
        this.mapper = mapper;
        this.specificationGenerator = specificationGenerator;
    }

    @Override
    public Page<ApiResource> findAll(MultiValueMap<String, String> filters, Pageable pageable) {
        return repository.findAll(specificationGenerator.generate(filters), pageable)
                         .map(mapper::toApi);
    }

    @Override
    public Optional<ApiResource> findById(Id id) {
        return repository.findById(id)
                         .map(mapper::toApi);
    }

    @Override
    public ApiResource save(ApiResource apiResource) {
        return mapper.toApi(repository.save(mapper.fromApi(apiResource)));
    }

    @Override
    public void deleteById(Id id) {
        repository.deleteById(id);
    }
}
