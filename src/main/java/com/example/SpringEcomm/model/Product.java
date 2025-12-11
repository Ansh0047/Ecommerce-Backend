package com.example.SpringEcomm.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date releaseDate;
    private int stockQuantity;
    private boolean productAvailable;


    public Product(int id) {
        this.id = id;
    }
}
