package com.example.Tissue_back.service.expectation;

import com.example.Tissue_back.controller.request.expectation.ExpectationDto;
import com.example.Tissue_back.entity.expectation.Expectation;
import com.example.Tissue_back.repository.event.EventRepository;
import com.example.Tissue_back.repository.expectation.ExpectationRepository;
import com.example.Tissue_back.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpectationServiceImpl implements ExpectationService {

    @Autowired
    private ExpectationRepository expectationRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private EventRepository eventRepository;


    @Override
    public Boolean register(ExpectationDto expectationDto) throws Exception {
        // 한 사람이 하나의 댓글만 달 수 있도록 해야 함

        log.info("get ID():" + expectationDto.getId()); // 기대평 등록한 아이디 맞게 들어옴

        List<Expectation> expectationList = expectationRepository.findByEventNo(expectationDto.getEventNo());

        log.info(String.valueOf(expectationList)); // 해당 eventNo을 가진 기대평 리스트 잘 나옴

        for(int i = 0; i < expectationList.size(); i++){
            Expectation expectation = expectationList.get(i);

            if(expectation.getId().equals(expectationDto.getId())) {
                return false;
            }
        }

        expectationRepository.save(expectationDto.toEntity());
        return true;

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
