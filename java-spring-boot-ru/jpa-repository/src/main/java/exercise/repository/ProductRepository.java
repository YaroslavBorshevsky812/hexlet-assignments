package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findByPriceBetween(int minPriceInclusive, int maxPriceInclusive);

    // Диапазон с сортировкой
    List<Product> findByPriceBetween(int minPriceInclusive, int maxPriceInclusive, Sort sort);

    // Минимальная цена с сортировкой
    List<Product> findByPriceGreaterThanEqual(int minPriceInclusive, Sort sort);

    // Максимальная цена с сортировкой
    List<Product> findByPriceLessThanEqual(int maxPriceInclusive, Sort sort);

    // Для случаев когда нужна только минимальная/максимальная цена без сортировки
    default List<Product> findByPriceGreaterThanEqual(int minPriceInclusive) {
        return findByPriceGreaterThanEqual(minPriceInclusive, Sort.unsorted());
    }

    default List<Product> findByPriceLessThanEqual(int maxPriceInclusive) {
        return findByPriceLessThanEqual(maxPriceInclusive, Sort.unsorted());
    }
    // END
}
