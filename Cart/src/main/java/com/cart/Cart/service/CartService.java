package com.cart.Cart.service;
import com.cart.Cart.execption.CartNotFoundException;
import com.cart.Cart.execption.TripNotFoundException;
import com.cart.Cart.execption.UserNotFoundException;
import com.cart.Cart.model.Cart;
import com.cart.Cart.model.Status;
import com.cart.Cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getOne(Integer id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElseThrow(() -> new CartNotFoundException("Cart " + id + " not found"));
    }

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public Cart addOne(Cart cart) {
        cart.setStatus(Status.Created);
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
        return cartRepository.findByStatus(Status.Paid);
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