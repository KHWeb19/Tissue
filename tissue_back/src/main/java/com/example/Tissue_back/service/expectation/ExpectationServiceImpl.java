package com.example.Tissue_back.service.expectation;

import com.example.Tissue_back.controller.request.expectation.ExpectationDto;
import com.example.Tissue_back.entity.expectation.Expectation;
import com.example.Tissue_back.repository.expectation.ExpectationRepository;
import com.example.Tissue_back.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExpectationServiceImpl implements ExpectationService {

    @Autowired
    private ExpectationRepository expectationRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public Expectation register(ExpectationDto expectationDto) {

//        String id = securityService.getMemberId(expectationDto.getId());
//
//        Expectation expectation = new Expectation();
//
//        expectation.setExpectContent(expectationDto.getExpectContent());
//        expectation.setId(id);
//
//        expectationRepository.save(expectation);

        Expectation expectation = expectationRepository.save(expectationDto.toEntity());

        return expectation;
    }

    @Override
    public List<Expectation> read(Long eventNo) {
        List<Expectation> expectationList = expectationRepository.findByEventNo(eventNo);

        return expectationList;
    }

    @Override
    public Expectation readOnly(Long expectNo) {
        Expectation expectation = expectationRepository.findById(expectNo).orElseThrow();

        return expectation;
    }

    @Override
    public void modify(Expectation expectation, ExpectationDto expectationDto) {
        expectation.updateContent(expectationDto);

        expectationRepository.save(expectation);
    }

    @Override
    public void remove(Expectation expectation) {
        expectationRepository.delete(expectation);
    }
}
