package com.encuentralofacil.encuentralofacil.repositories;

import com.encuentralofacil.encuentralofacil.entities.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, Long> {
    @Query(value = "SELECT sp.*, p.name as product_name FROM stores_products sp INNER JOIN products p ON sp.product_id = p.id WHERE sp.store_id=?", nativeQuery = true)
    public List<StoreProduct> findByStoreId(Long storeId);
}
