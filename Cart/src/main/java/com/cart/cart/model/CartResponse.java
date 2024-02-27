package com.cart.cart.model;

import com.cart.cart.domain.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartResponse {
    private Integer id;
    private Double price;
    private Status status;
    private UserResponse user;
    private TripResponse trip;
}
