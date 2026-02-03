package edu.aitu.oop.clinic.repository;

public interface IRepository<T> {

    void save(T entity);

    T findById(Long id);
}