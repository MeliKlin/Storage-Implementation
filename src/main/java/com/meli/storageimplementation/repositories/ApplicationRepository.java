package com.meli.storageimplementation.repositories;

import java.util.List;

public interface ApplicationRepository<T, K> {

    void save(T obj);
    List<T> list();
    T find(K id);
    void delete(T obj);

}
