package com.encuentralofacil.encuentralofacil.repositories;

import com.encuentralofacil.encuentralofacil.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    public Optional<Store> findByEmail(String email);
    @Query(value = "SELECT s.* FROM stores s WHERE LOWER(s.district) = LOWER(?)", nativeQuery = true)
    public List<Store> findStoresByDistrict(String district);
}
