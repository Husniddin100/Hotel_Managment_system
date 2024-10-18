package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.OrderRatingDTO;
import com.example.Hotel.managment.system.entity.OrderRatingEntity;
import com.example.Hotel.managment.system.repository.OrderRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderRatingService {
    private final OrderRatingRepository orderRatingRepository;

    public OrderRatingDTO addRating(OrderRatingDTO dto) {
        OrderRatingEntity rating = new OrderRatingEntity();
        rating.setOrderId(dto.getOrderId());
        rating.setRating(dto.getRating());
        rating.setComment(dto.getComment());
       orderRatingRepository.save(rating);

       dto.setId(rating.getId());
       return dto;
    }
}
