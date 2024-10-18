package com.example.Hotel.managment.system.repository;

import com.example.Hotel.managment.system.dto.filter.HotelFilterDTO;
import com.example.Hotel.managment.system.dto.filter.PaginationResultDTO;
import com.example.Hotel.managment.system.entity.HotelEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HotelCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public PaginationResultDTO<HotelEntity> filter(HotelFilterDTO filter, int page, int size) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (filter.getId() != null) {
            builder.append("AND id = :id ");
            params.put("id", filter.getId());
        }
        if (filter.getName() != null) {
            builder.append("AND name = :name ");
            params.put("name", filter.getName());
        }
        if (filter.getAddress() != null) {
            builder.append("AND address = :address ");
            params.put("address", filter.getAddress());
        }
        if (filter.getDescription() != null) {
            builder.append("AND description = :description ");
            params.put("description", filter.getDescription());
        }

        StringBuilder stringBuilder = new StringBuilder("FROM HotelEntity h WHERE 1=1 ");
        stringBuilder.append(builder);
        stringBuilder.append(" ORDER BY id DESC ");

        StringBuilder countBuilder = new StringBuilder("SELECT count(h) FROM HotelEntity h WHERE 1=1 ");
        countBuilder.append(builder);

        Query selectQuery = entityManager.createQuery(stringBuilder.toString());
        selectQuery.setMaxResults(size);
        selectQuery.setFirstResult((page - 1) * size);
        Query countQuery = entityManager.createQuery(countBuilder.toString());

        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());
        }

        List<HotelEntity> entityList = selectQuery.getResultList();
        Long totalElements = (Long) countQuery.getSingleResult();

        return new PaginationResultDTO<>(totalElements, entityList);
    }

}
