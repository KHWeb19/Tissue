package com.example.Tissue_back.controller.request.hall;

import com.example.Tissue_back.entity.hall.HallSeat;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@NoArgsConstructor
public class TicketingHallResponse {
    private Long hallNo;
    private String hallName;
    private int rowCnt;
    private int colCnt;
    private List<HallSeat> seats;
}
