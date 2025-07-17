package com.example.org.dto; // Updated package

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugDTO {
    private Long id;
    private String drugName;
    private String unit;
    private BigDecimal pricePerUnit;
    private String description;
    private String manufacturer;
    private Integer stockQuantity;
    private Integer minStockLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
