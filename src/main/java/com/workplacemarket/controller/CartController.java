package com.workplacemarket.controller;

import com.workplacemarket.model.CartItem;
import com.workplacemarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItem>> getUserCart(
            @RequestHeader("Authorization") String authHeader) {
        String userId = extractUserId(authHeader);
        return ResponseEntity.ok(cartService.getUserCart(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addToCart(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Map<String, Object> request) {

        String userId = extractUserId(authHeader);
        Long productId = Long.valueOf(request.get("productId").toString());
        Integer quantity = Integer.valueOf(request.get("quantity").toString());

        cartService.addToCart(userId, productId, quantity);

        return ResponseEntity.ok(Map.of(
                "message", "商品已成功加入购物车"
        ));
    }

    // 从Authorization头中提取用户ID的辅助方法
    private String extractUserId(String authHeader) {
        // 格式: "Bearer token"
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return "anonymous";
    }
}