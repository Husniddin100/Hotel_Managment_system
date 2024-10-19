package com.example.Hotel.managment.system.service;

import com.example.Hotel.managment.system.dto.PaymentDTO;
import com.example.Hotel.managment.system.entity.OrderEntity;
import com.example.Hotel.managment.system.entity.PaymentEntity;
import com.example.Hotel.managment.system.enums.PaymentStatus;
import com.example.Hotel.managment.system.exp.AppBadException;
import com.example.Hotel.managment.system.repository.OrderRepository;
import com.example.Hotel.managment.system.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentDTO createPayment(PaymentDTO dto) {
        Optional<OrderEntity>order=paymentRepository.findOrderId(dto.getOrderId());
        if (order.isEmpty()){
            throw new AppBadException("order not found ");
        }

        Optional<PaymentEntity>payment=paymentRepository.checkStatus(dto.getOrderId(),PaymentStatus.PAID);
        if (payment.isPresent()){
            log.error("payment already exist");
            throw new AppBadException("payment already exist ");
        }

        if (dto.getAmount() <= 0) {
            throw new RuntimeException("The payment amount cannot be zero or negative");
        }

        //check payment price
        if (dto.getAmount() < order.get().getOrderPrice()) {
            log.error("amount not enough {}",dto.getAmount());
            throw new RuntimeException("The amount you paid is less than the amount on the order.");
        } else if (dto.getAmount() > order.get().getOrderPrice()) {
            log.error("more than the requested amount {}",dto.getAmount());
            throw new RuntimeException("The amount you paid is more than the amount on the order.");
        }

        PaymentEntity entity=new PaymentEntity();
        entity.setOrderId(dto.getOrderId());
        entity.setPaymentDate(LocalDateTime.now());
        entity.setAmount(dto.getAmount());
        entity.setStatus(PaymentStatus.PAID);

        paymentRepository.save(entity);
        log.info("payment successfully orderId {}",dto.getOrderId());
        return toDTO(entity);
    }

    public PaymentDTO getById(String id) {
        var payment = paymentRepository.findById(id).
                orElse(null);
           if (payment == null) {
               log.warn("Payment with id {} not found", id);
               throw new AppBadException("payment not found");
           }
           return toDTO(payment);
    }
    public PageImpl getAllByPagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "paymentDate");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<PaymentEntity> paymentPage = paymentRepository.findAll(paging);

        List<PaymentEntity> entityList = paymentPage.getContent();
        Long totalElements = paymentPage.getTotalElements();

        List<PaymentDTO> dtoList = new LinkedList<>();
        for (PaymentEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }
        return new PageImpl<>(dtoList, paging, totalElements);
    }

    public PaymentDTO toDTO(PaymentEntity entity) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setStatus(entity.getStatus());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setOrderId(entity.getOrderId());
        return dto;
    }
}
