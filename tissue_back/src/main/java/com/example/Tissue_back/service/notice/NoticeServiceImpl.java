package com.example.Tissue_back.service.notice;

import com.example.Tissue_back.entity.notice.Notice;
import com.example.Tissue_back.repository.notice.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Transactional
    @Override
    public void register (Notice notice, @RequestParam(required = false) MultipartFile file) throws Exception {

        if (file != null) {
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            FileOutputStream writer = new FileOutputStream("C:\\khweb19\\Tissue\\tissue_front\\src\\assets\\uploadImg/" + fileName);

            writer.write(file.getBytes());
            writer.close();

            notice.setNoticeImg(fileName);

            noticeRepository.save(notice);
        }
        else {
            noticeRepository.save(notice);
        }
    }

    @Override
    @Transactional
    public List<Notice> list() {
        //Sort sort = Sort.by(Sort.Order.desc("noticeNo"), Sort.Order.desc("noticeNecessary"));

        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "noticeNecessary", "noticeNo"));
    }

    @Override
    public Notice read (Long noticeNo){
        Optional<Notice> maybeReadNotice = noticeRepository.findById(noticeNo);

        if(maybeReadNotice.equals(Optional.empty())) {
            log.info("Can't read notice");
            return null;
        }

        return maybeReadNotice.get();
    }

    @Override
    public void modify (Notice notice, @RequestParam(required = false) MultipartFile file) throws Exception {
        if (notice.getNoticeImg().equals(Optional.empty())) {
            Path filePath = Paths.get("C:\\khweb19\\Tissue\\tissue_front\\src\\assets\\uploadImg/" + notice.getNoticeImg());
            Files.delete(filePath);
        }

        if(file!=null) {
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            FileOutputStream writer = new FileOutputStream("C:\\khweb19\\Tissue\\tissue_front\\src\\assets\\uploadImg/" + fileName);
            writer.write(file.getBytes());
            writer.close();

            notice.setNoticeImg(fileName);
        }
        noticeRepository.save(notice);
    }

    @Override
    public void remove(Long noticeNo) {
        Optional<Notice> selectFile = noticeRepository.findById(noticeNo);
        Notice removeFile = selectFile.get();

        File file = new File("C:\\khweb19\\Tissue\\tissue_front\\src\\assets\\uploadImg/" + removeFile.getNoticeImg());

        if (file.exists()) {
            file.delete();
        }
        noticeRepository.deleteById(noticeNo);
    }

    @Override
    public int updateNoticeView (Long noticeNo) {
        return noticeRepository.updateNoticeView(noticeNo);
    }
}
