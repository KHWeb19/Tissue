package com.example.Tissue_back.entity.expectation;

import com.example.Tissue_back.entity.event.Event;
import com.example.Tissue_back.entity.performance.Performance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Expectation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="expect_no")
    private Long expectNo;

    @OneToOne
    @JoinColumn(name="event_no")
    private Event event;
    

}

