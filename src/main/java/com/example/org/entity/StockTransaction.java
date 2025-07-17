package com.example.org.entity;

import com.example.org.entity.Drug;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_id", nullable = false, referencedColumnName = "id")
    private Drug drug;

    @Column(name = "transaction_type", nullable = false, length = 10)
    // Consider using an Enum for transaction type: IMPORT, EXPORT
    private String transactionType;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @CreationTimestamp // Matches DEFAULT GETDATE()
    @Column(name = "transaction_date", nullable = false, updatable = false)
    private LocalDateTime transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "notes", columnDefinition = "NVARCHAR(MAX)")
    private String notes;
}
