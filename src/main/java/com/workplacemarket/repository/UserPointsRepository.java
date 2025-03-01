package com.workplacemarket.repository;

import com.workplacemarket.model.UserPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPointsRepository extends JpaRepository<UserPoints, String> {
}