package com.example.Tissue_back.service.qna;

import com.example.Tissue_back.entity.qna.QnaBest;
import com.example.Tissue_back.repository.qna.QnaBestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QnaBestServiceImpl implements QnaBestService {

    @Autowired
    private QnaBestRepository repository;

    @Override
    public void register (QnaBest qnaBest) {
        repository.save(qnaBest);
    }

    @Override
    @Transactional
    public List<QnaBest> list() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "qnaBestNo"));
    }

    @Override
    public QnaBest read (Long qnaBestNo){
        Optional<QnaBest> maybeReadQnaBest = repository.findById(qnaBestNo);

        if(maybeReadQnaBest.equals(Optional.empty())) {
            log.info("Can't read board");
            return null;
        }
        return maybeReadQnaBest.get();
    }

    @Override
    public void modify (QnaBest qnaBest) {
        repository.save(qnaBest);
    }

    @Override
    public void remove(Long qnaBestNo) {
        repository.deleteById(qnaBestNo);
    }
}
