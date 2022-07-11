package com.example.Tissue_back.service.expectation;

import com.example.Tissue_back.controller.request.expectation.ExpectationDto;
import com.example.Tissue_back.entity.expectation.Expectation;

import java.util.List;

public interface ExpectationService{
    public Boolean register(ExpectationDto expectationDto) throws Exception;
//    public Boolean checkId(String expectId);
    public List<Expectation> read(Long eventNo);
    public Expectation readOnly(Long expectNo);
    public void modify(Expectation expectation, ExpectationDto expectationDto);
    public void remove(Expectation expectation);
}
