package com.andrew.order_service.service;

import com.andrew.order_service.client.InventoryClient;
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
    private final InventoryClient inventoryClient;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        //Verify that order is in stock
        boolean isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (isProductInStock) {
            //map order request to Order object
            Order orderDbModel = mapToDbModel(orderRequest);

            //save order to OrderRepository
            return mapToResponse(orderRepository.save(orderDbModel));
        } else {
            throw new RuntimeException("Product with SkuCode [" + orderRequest.skuCode() + "] is not in stock");
        }


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
