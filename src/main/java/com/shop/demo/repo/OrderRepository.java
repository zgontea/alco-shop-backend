package com.shop.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.demo.model.Order;
import com.shop.demo.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o FROM Order o WHERE o.user = ?1 AND o.status = 'Draft'", nativeQuery = false)
    public List<Order> getByOwnerUser(User user, String status);

    @Query(value = "SELECT o FROM Order o WHERE o.user = ?1 AND o.status != 'Draft'", nativeQuery = false)
    public List<Order> getAllByOwnerUser(User user);
}