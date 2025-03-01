package com.workplacemarket.repository;

import com.workplacemarket.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserId(String userId);

    // 修改了查询方法，因为CartItem现在直接引用productId，而不是Product对象
    @Query("SELECT c FROM CartItem c WHERE c.userId = :userId AND c.product.id = :productId")
    Optional<CartItem> findByUserIdAndProductId(@Param("userId") String userId, @Param("productId") Long productId);
}