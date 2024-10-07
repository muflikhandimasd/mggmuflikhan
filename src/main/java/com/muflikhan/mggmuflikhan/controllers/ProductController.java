package com.muflikhan.mggmuflikhan.controllers;

import com.muflikhan.mggmuflikhan.dtos.ApiResponse;
import com.muflikhan.mggmuflikhan.dtos.CreateOrUpdateProductRequest;
import com.muflikhan.mggmuflikhan.entities.Product;
import com.muflikhan.mggmuflikhan.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        ApiResponse<List<Product>> response = new ApiResponse<>(
                "success",
                products,
                "Data found",
                null
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> create(@RequestBody CreateOrUpdateProductRequest request){
        Product product=  productService.create(request);
        ApiResponse<Product> response = new ApiResponse<>(
                "success",
                product,
                "Product Created Successfully",
                null
        );

        return  ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> update(@PathVariable Long id, @RequestBody CreateOrUpdateProductRequest request) {
       Product product=  productService.update(id, request);
        ApiResponse<Product> response = new ApiResponse<>(
                "success",
                product,
                "Product Updated Successfully",
                null
        );

        return  ResponseEntity.ok(response);

    }




    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id){
        productService.delete(id);
        ApiResponse<Object> response = new ApiResponse<>(
                "success",
                null,
                "Product Deleted Successfully",
                null
        );

        return  ResponseEntity.ok(response);
    }


}
