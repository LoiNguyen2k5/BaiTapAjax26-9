package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}