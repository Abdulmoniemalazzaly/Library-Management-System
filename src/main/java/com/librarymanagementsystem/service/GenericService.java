package com.librarymanagementsystem.service;

import com.librarymanagementsystem.model.AuditorEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericService<T extends AuditorEntity> {

    public abstract JpaRepository getRepo();

    @Cacheable(value = "caching")
    public List<T> findAll(){
        return getRepo().findAll();
    }

    @CachePut
    public void saveEntity(T entity){
        getRepo().save(entity);
    }

    @CachePut
    public void updateEntity(T entity , Long id) throws Throwable {
        getRepo().findById(id)
                        .orElseThrow(() -> new RuntimeException(""));
        getRepo().save(entity);
    }

    @CacheEvict(key="#id")
    public void deleteEntityById(Long id) throws Throwable {
        getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("There no such book id!"));
        getRepo().deleteById(id);
    }

    @Cacheable(value = "caching" ,key = "#id")
    public T findById(Long id) throws Throwable {
        return (T) getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("There no such book id!"));
    }
}
