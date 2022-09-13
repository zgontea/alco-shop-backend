package com.shop.demo.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public static final String filteredQueryProducts = "SELECT * " +
            "FROM products " +
            "LEFT JOIN categories ON products.category_id = categories.category_id " +
            "WHERE categories.name = ?3 " +
            "AND products.name LIKE %?4% " +
            "AND products.unit_price >= ?5 " +
            "AND products.unit_price <= ?6 " +
            "OFFSET ?1 LIMIT ?2";

    public static final String filteredQueryProductsCount = "SELECT COUNT(product_id) " +
            "FROM products " +
            "LEFT JOIN categories ON products.category_id = categories.category_id " +
            "WHERE categories.name = ?1 " +
            "AND products.name LIKE %?2% " +
            "AND products.unit_price >= ?3 " +
            "AND products.unit_price <= ?4";

    public static final String filteredQueryProductsCategoryAll = "SELECT * " +
            "FROM products " +
            "LEFT JOIN categories ON products.category_id = categories.category_id " +
            "WHERE products.name LIKE %?3% " +
            "AND products.unit_price >= ?4 " +
            "AND products.unit_price <= ?5 " +
            "OFFSET ?1 LIMIT ?2";

    public static final String filteredQueryProductsCountCategoryAll = "SELECT COUNT(product_id) " +
            "FROM products " +
            "LEFT JOIN categories ON products.category_id = categories.category_id " +
            "WHERE products.name LIKE %?1% " +
            "AND products.unit_price >= ?2 " +
            "AND products.unit_price <= ?3";

    @Query(value = filteredQueryProducts, nativeQuery = true)
    public List<Product> getFilteredProducts(int offset, int limit, String category, String productName, BigDecimal unitPriceMin, BigDecimal unitPriceMax);

    @Query(value = filteredQueryProductsCategoryAll, nativeQuery = true)
    public List<Product> getFilteredProducts(int offset, int limit, String productName, BigDecimal unitPriceMin, BigDecimal unitPriceMax);

    @Query(value = filteredQueryProductsCount, nativeQuery = true)
    public long getFilteredProductsCount(String category, String productName, BigDecimal unitPriceMin, BigDecimal unitPriceMax);

    @Query(value = filteredQueryProductsCountCategoryAll, nativeQuery = true)
    public long getFilteredProductsCount(String productName, BigDecimal unitPriceMin, BigDecimal unitPriceMax);
}