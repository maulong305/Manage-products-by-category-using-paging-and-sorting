package com.example.producuploadfile.service;


import com.example.producuploadfile.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Iterable<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Page<Product> search(String keyword, Pageable pageable);

    void delete(Long id);

    Product findById(Long id);

    Product save(Product product);


}
