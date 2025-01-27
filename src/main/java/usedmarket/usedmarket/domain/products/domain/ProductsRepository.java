package usedmarket.usedmarket.domain.products.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import usedmarket.usedmarket.domain.category.domain.Category;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitleContaining(String keyword, Pageable pageable);
    List<Product> findByCategory(Category category, Pageable pageable);
}
