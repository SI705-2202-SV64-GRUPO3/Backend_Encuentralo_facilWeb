package com.encuentralofacil.encuentralofacil.repositories;

import com.encuentralofacil.encuentralofacil.entities.Product;
import com.encuentralofacil.encuentralofacil.entities.StoreOrderBudget;
import com.encuentralofacil.encuentralofacil.entities.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, Long> {
    @Query(value = "SELECT sp.*, p.name as product_name FROM stores_products sp INNER JOIN products p ON sp.product_id = p.id WHERE sp.store_id=?", nativeQuery = true)
    public List<StoreProduct> findByStoreId(Long storeId);

    @Query(nativeQuery = true, value = "SELECT s.name as storeName, s.id as storeId, p.id as productId, sum(sp.price) as totalPrice" +
            " FROM stores_products sp INNER JOIN products p ON sp.product_id = p.id INNER JOIN stores s ON sp.store_id = s.id" +
            " WHERE LOWER(s.district) = LOWER(:district) AND p.id IN :productIds GROUP BY (s.name, s.id, p.id)")
    public List<StoreOrderBudgetConverter> getComparison(@Param("productIds") Collection<Long> productsIds, @Param("district") String district);

    @Query(value = "SELECT sp.* FROM stores_products sp WHERE sp.product_id IN :productsIds AND sp.store_id = :storeId", nativeQuery = true)
    public List<StoreProduct> findProductsIdsAndStore(@Param("productsIds") Collection<Long> productsIds, @Param("storeId") Long storeId);
}
