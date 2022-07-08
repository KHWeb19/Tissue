package com.example.Tissue_back.controller.expectation;

import com.example.Tissue_back.controller.request.expectation.ExpectationDto;
import com.example.Tissue_back.entity.expectation.Expectation;
import com.example.Tissue_back.service.expectation.ExpectationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/expectation")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ExpectationController {

    @Autowired
    private ExpectationService expectationService;

    @PostMapping("/register")
    public Boolean register (@RequestBody ExpectationDto expectationDto) throws Exception {
        log.info("expectation Register()");

        Boolean expectation = expectationService.register(expectationDto);

        return expectation;
    }

    @GetMapping("/read/{eventNo}")
    public ResponseEntity read(@PathVariable Long eventNo) {
        log.info("expectation Read()");

        List<Expectation> expectList = expectationService.read(eventNo);

        return new ResponseEntity(expectList, HttpStatus.OK);
    }

    @GetMapping("/read/only/{expectNo}")
    public ResponseEntity readOnly(@PathVariable Long expectNo) {
        log.info("expectation ReadOnly()");

        Expectation expectation = expectationService.readOnly(expectNo);

        return new ResponseEntity(expectation, HttpStatus.OK);
    }

    @PatchMapping("/modify/{expectNo}")
    public ResponseEntity<Void> modify(@PathVariable Long expectNo,
                                       @RequestBody ExpectationDto expectationDto) {
        log.info("expectation Modify()");

        Expectation expectation = expectationService.readOnly(expectNo);
        expectationService.modify(expectation, expectationDto);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/remove/{expectNo}")
    public ResponseEntity<Void> remove(@PathVariable Long expectNo){
        log.info("expectation Remove()");

        Expectation expectation = expectationService.readOnly(expectNo);
        expectationService.remove(expectation);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
