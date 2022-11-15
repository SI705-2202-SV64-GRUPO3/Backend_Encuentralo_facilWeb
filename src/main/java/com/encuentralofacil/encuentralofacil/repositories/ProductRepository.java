package com.encuentralofacil.encuentralofacil.repositories;

import com.encuentralofacil.encuentralofacil.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT DISTINCT p.* FROM products p INNER JOIN stores_products sp ON p.id = sp.product_id\n" +
            "INNER JOIN stores s ON s.id = sp.store_id WHERE LOWER(s.district) = LOWER(?);", nativeQuery = true)
    public List<Product> getCommonProductsInStoresByDistrict(String district);

    @Query(value = "SELECT p.* FROM products p WHERE p.category_id = ?", nativeQuery = true)
    public List<Product> findByCategory(String categoryId);
}
