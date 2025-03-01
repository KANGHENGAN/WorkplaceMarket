package com.workplacemarket.service;

import com.workplacemarket.model.PointsProduct;
import com.workplacemarket.model.UserPoints;
import com.workplacemarket.repository.PointsProductRepository;
import com.workplacemarket.repository.UserPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PointsService {

    @Autowired
    private PointsProductRepository pointsProductRepository;

    @Autowired
    private UserPointsRepository userPointsRepository;

    public List<PointsProduct> getAllPointsProducts() {
        return pointsProductRepository.findAll();
    }

    public Optional<PointsProduct> getPointsProductById(Long id) {
        return pointsProductRepository.findById(id);
    }

    public int getUserPoints(String userId) {
        Optional<UserPoints> userPoints = userPointsRepository.findById(userId);
        return userPoints.map(UserPoints::getPoints).orElse(0);
    }

    @Transactional
    public boolean redeemPoints(String userId, Long productId) {
        Optional<PointsProduct> productOpt = pointsProductRepository.findById(productId);

        if (productOpt.isEmpty()) {
            return false;
        }

        PointsProduct product = productOpt.get();
        int pointsRequired = product.getPointsRequired();

        // 获取用户积分
        UserPoints userPoints = userPointsRepository.findById(userId)
                .orElse(new UserPoints(userId, 0));

        // 检查积分是否足够
        if (userPoints.getPoints() < pointsRequired) {
            return false;
        }

        // 扣除积分
        userPoints.setPoints(userPoints.getPoints() - pointsRequired);
        userPointsRepository.save(userPoints);

        return true;
    }
}