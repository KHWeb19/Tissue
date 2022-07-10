package com.example.Tissue_back;

import com.example.Tissue_back.controller.request.ticketing.TicketingDto;
import com.example.Tissue_back.entity.hall.Hall;
import com.example.Tissue_back.repository.hall.HallRepository;
import com.example.Tissue_back.service.hall.HallSeatService;
import com.example.Tissue_back.service.hall.HallService;
import com.example.Tissue_back.service.ticketing.TicketingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TissueBackApplicationTests {


	@Autowired
	TicketingService ticketingService;
	@Test
	void 좌석테스트() {
		TicketingDto ticketingDto = new TicketingDto();

		String[] arr = {"15","20","25"};

		ticketingDto.setSeatNameArr(arr);



	}

}
