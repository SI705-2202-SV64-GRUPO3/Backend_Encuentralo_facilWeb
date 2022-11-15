package com.encuentralofacil.encuentralofacil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.encuentralofacil.encuentralofacil.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
