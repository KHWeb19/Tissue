package com.example.Tissue_back.controller.request.expectation;

import com.example.Tissue_back.entity.expectation.Expectation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpectationDto {

    private Long expectNo;
    private Long eventNo;
    private String id;
    private String expectContent;
    private Date regDate;
    private Date updDate;

    public Expectation toEntity() {
        return Expectation.builder()
                .expectNo(expectNo)
                .eventNo(eventNo)
                .id(id)
                .expectContent(expectContent)
                .regDate(regDate)
                .updDate(updDate)
                .build();
    }
}
