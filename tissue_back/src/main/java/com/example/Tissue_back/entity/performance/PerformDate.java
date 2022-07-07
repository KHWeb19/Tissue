package com.example.Tissue_back.entity.performance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PerformDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="perform_date_no")
    private Long performDateNo;

    private Long performNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate performDate;
}
