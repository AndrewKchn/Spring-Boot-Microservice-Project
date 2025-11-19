package com.andrew.order_service.service;

import com.andrew.order_service.dto.OrderRequest;
import com.andrew.order_service.dto.OrderResponse;
import com.andrew.order_service.model.Order;
import com.andrew.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
//    private final WebClient.Builder webClientBuilder;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        //map order request to Order object
        Order orderDbModel = mapToDbModel(orderRequest);

        //save order to OrderRepository
        Order savedOrder = orderRepository.save(orderDbModel);
        return mapToResponse(savedOrder);
    }

    private Order mapToDbModel(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setSkuCode(orderRequest.skuCode());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        return order;
    }

    private OrderResponse mapToResponse(Order orderDbModel) {
        return new OrderResponse(orderDbModel.getId(),
                orderDbModel.getOrderNumber(),
                orderDbModel.getSkuCode(),
                orderDbModel.getPrice(),
                orderDbModel.getQuantity());
    }
}
