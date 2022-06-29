package com.example.Tissue_back.controller.QnA;

import com.example.Tissue_back.entity.member.Member;
import com.example.Tissue_back.entity.qna.Qna;
import com.example.Tissue_back.entity.qna.QnaBest;
import com.example.Tissue_back.service.qna.QnaBestService;
import com.example.Tissue_back.service.qna.QnaService;
import com.example.Tissue_back.service.security.SecurityService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qnaBest")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QnaBestController {

    @Autowired
    private QnaBestService service;

    @Autowired
    private SecurityService securityService;

    @PostMapping("/register")
    public void QnaBestRegister (@Validated @RequestBody QnaBest qnaBest){
        log.info("Qna Best Register");

        service.register(qnaBest);
    }

    @GetMapping("/list")
    public List<QnaBest> QnaBestList() {
        log.info("Qna Best List");

        return service.list();
    }

    @GetMapping("/getRole")
    public Claims getInfo (@RequestParam(value = "token") String token) {
        log.info ("== Get Role ==" + token);

        return service.getRole(token);
    }


    /*@GetMapping("/checkRole")
    public Boolean admin (@RequestParam(value="token") String token) {
        log.info("==is role admin?==" + token);
        Claims role = securityService.getRole(token);

        log.info("role" + role.get("roles"));
        if (!role.get("roles").toString().equals("ADMIN")){
            return false;
        }
        return true;
    }*/

    @GetMapping("/{qnaBestNo}")
    public QnaBest QnaBestRead (@PathVariable("qnaBestNo") Long qnaBestNo){
        log.info("Qna Best Read");

        return service.read(qnaBestNo);
    }

    @PutMapping("/{qnaBestNo}")
    public QnaBest QnaBestModify (@PathVariable("qnaBestNo") Long qnaBestNo,
                          @RequestBody QnaBest qnaBest) {
        log.info("Qna Best Modify ");

        qnaBest.setQnaBestNo(qnaBestNo);
        service.modify(qnaBest);

        return qnaBest;
    }

    @DeleteMapping("/delete/{qnaBestNo}")
    public void QnaBestDelete (@PathVariable("qnaBestNo") Long qnaBestNo, QnaBest qnaBest) throws Exception {
        log.info("QnaBestDelete");

        service.remove(qnaBestNo);
    }
}
