package edu.aitu.oop.clinic.repository;

import java.util.List;

public interface IRepository<T> {
    void save(T entity);
    T findById(Long id);
    List<T> findAll();
}