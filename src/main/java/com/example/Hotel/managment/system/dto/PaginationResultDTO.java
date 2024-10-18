package com.example.Hotel.managment.system.dto;

import com.example.Hotel.managment.system.entity.BaseEntity;
import com.example.Hotel.managment.system.entity.RoomEntity;
import lombok.Data;

import java.util.List;

@Data
public class PaginationResultDTO<T extends BaseEntity> {
    private Long totalSize;
    private List<T>list;

    public PaginationResultDTO(Long totalSize, List<T> list) {
        this.totalSize = totalSize;
        this.list = list;
    }
}
