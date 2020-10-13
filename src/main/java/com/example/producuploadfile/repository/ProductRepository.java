package com.example.producuploadfile.repository;

import com.example.producuploadfile.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAllByNameContains(String name, Pageable pageable);
}
