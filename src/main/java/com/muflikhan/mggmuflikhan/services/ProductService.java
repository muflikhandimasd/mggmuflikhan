package com.muflikhan.mggmuflikhan.services;

import com.muflikhan.mggmuflikhan.dtos.CreateOrUpdateProductRequest;
import com.muflikhan.mggmuflikhan.entities.Product;
import com.muflikhan.mggmuflikhan.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ValidationService validationService;

    public List<Product> getAllProducts() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Transactional
    public Product create(CreateOrUpdateProductRequest request){
        validationService.validate(request);
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        return  productRepository.save(product);
    }

    @Transactional
    public Product update(Long id,CreateOrUpdateProductRequest request){
        validationService.validate(request);

        Product product = productRepository.findById(id).orElseThrow();

        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return  productRepository.save(product);

    }

    @Transactional
    public void delete(Long id){
        productRepository.delete(productRepository.findById(id).orElseThrow());
    }



}
