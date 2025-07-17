package com.example.org.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "drugs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drug_name", nullable = false, length = 255, unique = true)
    private String drugName;

    @Column(name = "unit", length = 50)
    private String unit;

    @Column(name = "price_per_unit", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerUnit;

    @Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "manufacturer", length = 100)
    private String manufacturer;

    @Column(name = "stock_quantity", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer stockQuantity;

    @Column(name = "min_stock_level", columnDefinition = "INT DEFAULT 0")
    private Integer minStockLevel;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Relationships
    @OneToMany(mappedBy = "drug", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PrescriptionItem> prescriptionItems = new HashSet<>();

    @OneToMany(mappedBy = "drug", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StockTransaction> stockTransactions = new HashSet<>();
}
