package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.dto.PaginationResultDTO;
import com.example.Hotel.managment.system.dto.RoomFilterDTO;
import com.example.Hotel.managment.system.entity.RoomEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoomCustomRepository {
    @Autowired
    private EntityManager entityManager;

        public PaginationResultDTO<RoomEntity> filter(RoomFilterDTO filter, int page, int size) {
            StringBuilder builder = new StringBuilder();
            Map<String, Object> params = new HashMap<>();

            if (filter.getId() != null) {
                builder.append("and id = :id ");
                params.put("id", filter.getId());
            }
            if (filter.getNumber() != null) {
                builder.append("and number = :number ");
                params.put("number", filter.getNumber());
            }
            if (filter.getStatus() != null) {
                builder.append("and room_status = :status ");
                params.put("status", filter.getStatus());
            }
            if (filter.getCategory() != null) {
                builder.append("and category = :category ");
                params.put("category", filter.getCategory());
            }
            if (filter.getPrice() != null) {
                builder.append("and price = :price ");
                params.put("price", filter.getPrice());
            }
            if (filter.getHotelId() != null) {
                builder.append("and hotel_id = :hotelId ");
                params.put("hotelId", filter.getHotelId());
            }

            StringBuilder selectBuilder = new StringBuilder("FROM room r where 1=1 ");
            selectBuilder.append(builder);
            selectBuilder.append(" order by id desc ");

            StringBuilder countBuilder = new StringBuilder("SELECT count(r) FROM room r where 1=1 ");
            countBuilder.append(builder);

            Query selectQuery = entityManager.createQuery(selectBuilder.toString());
            selectQuery.setMaxResults(size);
            selectQuery.setFirstResult((page - 1) * size);
            Query countQuery = entityManager.createQuery(countBuilder.toString());

            for (Map.Entry<String, Object> param : params.entrySet()) {
                selectQuery.setParameter(param.getKey(), param.getValue());
                countQuery.setParameter(param.getKey(), param.getValue());
            }
            List<RoomEntity> roomList = selectQuery.getResultList();
            Long totalElements = (Long) countQuery.getSingleResult();

            return new PaginationResultDTO<>(totalElements, roomList);
        }
    }

