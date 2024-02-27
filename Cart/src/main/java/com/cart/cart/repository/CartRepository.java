package com.cart.cart.repository;

import com.cart.cart.domain.Cart;
import com.cart.cart.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByStatus(Status status);
    List<Cart> findByUserId(Integer userId);
    List<Cart> findByTripId(Integer tripId);

}
