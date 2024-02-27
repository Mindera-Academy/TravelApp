package com.cart.cart.service;
import com.cart.cart.execption.CartNotFoundException;
import com.cart.cart.execption.TripNotFoundException;
import com.cart.cart.execption.UserNotFoundException;
import com.cart.cart.domain.Cart;
import com.cart.cart.domain.Status;
import com.cart.cart.model.CartResponse;
import com.cart.cart.model.TripResponse;
import com.cart.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final TripService tripService;

    public CartResponse getOne(Integer id) {
        Optional<Cart> optCart = cartRepository.findById(id);
        Cart cart = optCart.orElseThrow(() -> new CartNotFoundException("Cart " + id + " not found"));
        return CartResponse.builder()
                .id(cart.getId())
                .status(cart.getStatus())
                .price(cart.getPrice())
                .trip(tripService.getTripById(cart.getTripId()))
                .user(null)
                .build();
    }

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public Cart addOne(Cart cart) {
        cart.setStatus(Status.CREATED);
        return cartRepository.save(cart);
    }

    public void update(Integer id, Cart cart) {
        validateCartNotFound(cartRepository.findById(id), id, " not found!");
        cart.setId(id);
        cartRepository.save(cart);
    }

    public void remove(Integer id) {
        Optional<Cart> cart = cartRepository.findById(id);
        cart.orElseThrow(() -> new CartNotFoundException("Cart " + id + " not found"));
        cartRepository.delete(cart.get());
    }

    public List<Cart> getPaidOrders() {
        return cartRepository.findByStatus(Status.PAID);
    }

    public List<Cart> getOrdersByUserId(Integer userId) {
        if (userId <= 0) {
            throw new UserNotFoundException("Invalid user ID: " + userId);
        }
        return cartRepository.findByUserId(userId);
    }

    public List<Cart> getOrdersByTripId(Integer tripId) {
        if (tripId <= 0) {
            throw new TripNotFoundException("Invalid trip ID: " + tripId);
        }
        return cartRepository.findByTripId(tripId);
    }

    private void validateCartNotFound(Optional<Cart> cartRepository, Integer id, String x) {
        if (cartRepository.isEmpty()) {
            throw new CartNotFoundException("Cart " + id + x);
        }
    }
}