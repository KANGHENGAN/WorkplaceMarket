package com.workplacemarket.controller;

import com.workplacemarket.model.PointsProduct;
import com.workplacemarket.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/points")
@CrossOrigin(origins = "*")
public class PointsController {

    @Autowired
    private PointsService pointsService;

    @GetMapping("/products")
    public ResponseEntity<List<PointsProduct>> getAllPointsProducts() {
        return ResponseEntity.ok(pointsService.getAllPointsProducts());
    }

    @GetMapping("/user")
    public ResponseEntity<Map<String, Integer>> getUserPoints(
            @RequestHeader("Authorization") String authHeader) {
        String userId = extractUserId(authHeader);
        int points = pointsService.getUserPoints(userId);
        return ResponseEntity.ok(Map.of("points", points));
    }

    @PostMapping("/redeem")
    public ResponseEntity<?> redeemPoints(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Map<String, Long> request) {

        String userId = extractUserId(authHeader);
        Long productId = request.get("productId");

        boolean success = pointsService.redeemPoints(userId, productId);

        if (success) {
            int remainingPoints = pointsService.getUserPoints(userId);
            return ResponseEntity.ok(Map.of(
                    "message", "兑换成功",
                    "remainingPoints", remainingPoints
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "兑换失败，积分不足或商品不存在"
            ));
        }
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