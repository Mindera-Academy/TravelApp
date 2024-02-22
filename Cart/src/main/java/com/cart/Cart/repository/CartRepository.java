package com.cart.Cart.repository;

import com.cart.Cart.model.Cart;
import com.cart.Cart.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByStatus(Status status);
    List<Cart> findByUserId(Integer userId);
    List<Cart> findByTripId(Integer tripId);

}
