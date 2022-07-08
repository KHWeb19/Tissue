package com.example.Tissue_back.service.expectation;

import com.example.Tissue_back.controller.request.expectation.ExpectationDto;
import com.example.Tissue_back.entity.expectation.Expectation;
import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.repository.expectation.ExpectationRepository;
import com.example.Tissue_back.service.security.SecurityService;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpectationServiceImpl implements ExpectationService {

    @Autowired
    private ExpectationRepository expectationRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public Boolean register(ExpectationDto expectationDto) throws Exception {

//        String id = securityService.getMemberId(token)
//        Expectation expectation = new Expectation();
//
//        expectation.setExpectContent(expectationDto.getExpectContent());
//        expectation.setId(id);
//
//        expectationRepository.save(expectation);

        String memberId = expectationDto.getId();

        Optional<Expectation> checkId = expectationRepository.findByExpectId(memberId);

        if(checkId.isPresent()) {
            return false;
        }
        else {
            expectationRepository.save(expectationDto.toEntity());
            return true;
        }
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
