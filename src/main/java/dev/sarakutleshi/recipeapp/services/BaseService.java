package dev.sarakutleshi.recipeapp.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseService<T, TId> {
    List<T> findAll();

    T findById(TId id);

    T add(T model);

    T modify(TId id, T model);

    void removeById(TId id);


}
