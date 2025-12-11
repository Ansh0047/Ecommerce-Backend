package com.example.SpringEcomm.controller;

import com.example.SpringEcomm.model.Product;
import com.example.SpringEcomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api")       // here this route will be common for all
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

//    @GetMapping ("/products")
//    public List<Product> getProducts () {
//        return productService.getAllProducts();
//    }

    // to pass the status code along with the data we use response entity
    @GetMapping ("/products")
    public ResponseEntity<List<Product>> getProducts () {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.ACCEPTED);
    }

    @GetMapping ("/product/{id}")
    public ResponseEntity<Product> getProducts (@PathVariable  int id) {
        Product product = productService.getProductById(id);

        if(product.getId() > 0){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new Product(-1),HttpStatus.NOT_FOUND);
        }
    }
}
