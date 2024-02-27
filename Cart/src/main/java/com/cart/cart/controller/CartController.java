package com.cart.cart.controller;

import com.cart.cart.execption.CartNotFoundException;
import com.cart.cart.execption.TripNotFoundException;
import com.cart.cart.execption.UserNotFoundException;
import com.cart.cart.domain.Cart;
import com.cart.cart.model.CartResponse;
import com.cart.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable Integer id) {
        CartResponse cart = cartService.getOne(id);
        return ResponseEntity.ok(cart);
    }

    @GetMapping()
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAll();
        return ResponseEntity.ok(carts);
    }

    @PostMapping()
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        Cart added = cartService.addOne(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(added);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCart(@PathVariable Integer id, @RequestBody Cart cart) {
        cartService.update(id, cart);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer id) {
        cartService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/paid")
    public ResponseEntity<List<Cart>> getPaidOrders() {
        List<Cart> paidOrders = cartService.getPaidOrders();
        return ResponseEntity.ok(paidOrders);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getOrdersByUserId(@PathVariable Integer userId) {
        List<Cart> orders = cartService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @ExceptionHandler(CartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleCartNotFoundException(CartNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TripNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleTripNotFoundException(TripNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
