package com.encuentralofacil.encuentralofacil.repositories;

import com.encuentralofacil.encuentralofacil.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p.* FROM products p WHERE p.category_id = ?", nativeQuery = true)
    public List<Product> findByCategory(String categoryId);
    @Query(value = "SELECT p.* FROM products p INNER JOIN stores_products sp ON p.id = sp.product_id" +
            " INNER JOIN stores s ON s.id = sp.store_id WHERE s.id in :storeIds", nativeQuery = true)
    public List<Product> findInStoreIds(@Param("storeIds") Collection<Long> storeIds);
    @Query(value = "SELECT p.* from products p WHERE p.id IN :ids", nativeQuery = true)
    public List<Product> findInIds(@Param("ids") Collection<Long> ids);
    @Query(value = "SELECT p.* from products p WHERE p.id IN :ids AND p.category_id = :categoryId", nativeQuery = true)
    public List<Product> findInIdsAndCategory(@Param("ids") Collection<Long> ids, @Param("categoryId") String categoryId);
}
