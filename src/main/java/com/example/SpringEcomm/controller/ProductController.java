package com.example.SpringEcomm.controller;

import com.example.SpringEcomm.model.Product;
import com.example.SpringEcomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

        if(product.getId() != -1){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new Product(-1),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping ("/product/{id}/image")
    public ResponseEntity<byte[]> getImageByProductId (@PathVariable int id){
        Product product = productService.getProductById(id);

        if(product.getId() != -1){
            return new ResponseEntity<>(product.getImageData(),HttpStatus.OK);        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping ("/product")
    public ResponseEntity<?> addProduct (@RequestPart Product product, @RequestPart MultipartFile imageFile){
        Product savedProduct = null;

        try {
            savedProduct = productService.addProduct(product,imageFile);
            return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // return "product saved successfully...";
    }
}
