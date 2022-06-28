package com.example.Tissue_back.entity;

import com.example.Tissue_back.controller.request.map.MapDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_no")
    private Long mapNo;

    @Column
    private Long performNo;

    @Column
    private double x;

    @Column
    private double y;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String url;

    @CreationTimestamp
    private Date regDate;

    public void updateMap(MapDto mapDto) {
        this.x = mapDto.getX();
        this.y = mapDto.getY();
        this.name = mapDto.getName();
        this.address = mapDto.getAddress();
        this.phone = mapDto.getPhone();
        this.url = mapDto.getUrl();
    }
}
