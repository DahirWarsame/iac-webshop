package com.iacteam2.webshop.repository;

import com.iacteam2.webshop.model.OrderRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRuleRepository extends JpaRepository<OrderRule, Long> {
}
