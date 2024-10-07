package com.muflikhan.mggmuflikhan.repositories;

import com.muflikhan.mggmuflikhan.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
