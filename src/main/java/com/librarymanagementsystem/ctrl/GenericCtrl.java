package com.librarymanagementsystem.ctrl;

import com.librarymanagementsystem.model.AuditorEntity;
import com.librarymanagementsystem.service.GenericService;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


public abstract class GenericCtrl<T extends AuditorEntity> {

    private String entityName;
    public abstract GenericService<T> getService();

    @GetMapping
    public ResponseEntity<List<T>> findAll(){
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@Valid @PathVariable(name = "id") Long id) throws Throwable {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveEntity(@Valid @RequestBody T entity){
        getService().saveEntity(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEntity(@Valid @RequestBody T entity , @PathVariable(name = "id") Long id) throws Throwable {
        getService().updateEntity(entity , id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) throws Throwable {
        getService().deleteEntityById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
