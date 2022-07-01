package com.example.Tissue_back.entity.expectation;

import com.example.Tissue_back.controller.request.expectation.ExpectationDto;
import com.example.Tissue_back.entity.event.Event;
import com.example.Tissue_back.entity.performance.Performance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Expectation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="expect_no")
    private Long expectNo;

    private Long eventNo;

    private String id;

    @Column(name="expect_content")
    private String expectContent;

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date updDate;

    public void updateContent(ExpectationDto expectationDto) {
        this.expectContent = expectationDto.getExpectContent();
    }


}

