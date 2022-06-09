package com.example.Tissue_back;

import com.example.Tissue_back.entity.hall.Hall;
import com.example.Tissue_back.repository.hall.HallRepository;
import com.example.Tissue_back.service.hall.HallService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TissueBackApplicationTests {


	@Autowired
	HallService hallService;

	Hall hall = new Hall();
	@Test
	void 홀등록테스트() {

	}

}
