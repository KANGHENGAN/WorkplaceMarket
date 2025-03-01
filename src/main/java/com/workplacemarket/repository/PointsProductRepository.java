package com.workplacemarket.repository;

import com.workplacemarket.model.PointsProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsProductRepository extends JpaRepository<PointsProduct, Long> {
}