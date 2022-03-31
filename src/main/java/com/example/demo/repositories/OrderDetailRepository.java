package com.example.demo.repositories;

import com.example.demo.entities.OrderDetail;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long>
{
}
