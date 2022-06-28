
package com.example.Tissue_back.controller.request.map;

import com.example.Tissue_back.entity.map.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MapDto {
    private Long mapNo;
    private Long performNo;
    private double x;
    private double y;
    private String name;
    private String address;
    private String phone;
    private String url;
    private Date regDate;

    public Map toEntity() {
        return Map.builder()
                .mapNo(mapNo)
                .performNo(performNo)
                .x(x)
                .y(y)
                .name(name)
                .address(address)
                .phone(phone)
                .url(url)
                .regDate(regDate)
                .build();
    }
}
