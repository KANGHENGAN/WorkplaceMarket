package com.workplacemarket.service;

import com.workplacemarket.model.CartItem;
import com.workplacemarket.model.Product;
import com.workplacemarket.repository.CartRepository;
import com.workplacemarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> getUserCart(String userId) {
        return cartRepository.findByUserId(userId);
    }

    @Transactional
    public CartItem addToCart(String userId, Long productId, Integer quantity) {
        // 查找产品
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new IllegalArgumentException("产品未找到");
        }

        Product product = productOpt.get();

        // 查找购物车中是否已有该产品
        Optional<CartItem> existingItemOpt = cartRepository.findByUserIdAndProductId(userId, productId);

        if (existingItemOpt.isPresent()) {
            // 更新数量
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            return cartRepository.save(existingItem);
        } else {
            // 添加新项
            CartItem newItem = new CartItem();
            newItem.setUserId(userId);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setAddedAt(LocalDateTime.now());
            return cartRepository.save(newItem);
        }
    }
}