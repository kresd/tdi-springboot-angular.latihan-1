package com.product.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.product.portal.model.Product;
import com.product.portal.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class ProductController {

    @Autowired
    ProductRepository repository;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        System.out.println("Get all Products...");

        List<Product> products = new ArrayList<>();
        repository.findAll().forEach(products::add);

        return products;
    }

    @PostMapping(value = "/products/create")
    public Product postProduct(@RequestBody Product product) {

        Product _product = repository.save(new Product(product.getId(), product.getProductName(), product.getDescription(), product.getCategory()));
        return _product;
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
        System.out.println("Delete Product with ID = " + id + "...");

        repository.deleteById(id);

        return new ResponseEntity<>("Product has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/products/delete")
    public ResponseEntity<String> deleteAllProducts() {
        System.out.println("Delete All Products...");

        repository.deleteAll();

        return new ResponseEntity<>("All products have been deleted!", HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        System.out.println("Update Product with ID = " + id + "...");

        Optional<Product> productData = repository.findById(id);

        if (productData.isPresent()) {
            Product _product = productData.get();
            _product.setId(product.getId());
            _product.setProductName(product.getProductName());
            _product.setDescription(product.getDescription());
            return new ResponseEntity<>(repository.save(_product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
