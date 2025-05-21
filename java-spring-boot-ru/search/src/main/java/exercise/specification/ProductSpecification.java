package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {
    public static Specification<Product> build(ProductParamsDTO params) {
        return Specification.where(titleContains(params.getTitleCont()))
                            .and(categoryIdEquals(params.getCategoryId()))
                            .and(priceLessThan(params.getPriceLt()))
                            .and(priceGreaterThan(params.getPriceGt()))
                            .and(ratingGreaterThan(params.getRatingGt()));
    }

    private static Specification<Product> titleContains(String titleCont) {
        return (root, query, cb) ->
            titleCont == null ? null :
            cb.like(cb.lower(root.get("title")), "%" + titleCont.toLowerCase() + "%");
    }

    private static Specification<Product> categoryIdEquals(Long categoryId) {
        return (root, query, cb) ->
            categoryId == null ? null :
            cb.equal(root.get("category").get("id"), categoryId);
    }

    private static Specification<Product> priceLessThan(Integer price) {
        return (root, query, cb) ->
            price == null ? null :
            cb.lessThan(root.get("price"), price);
    }

    private static Specification<Product> priceGreaterThan(Integer price) {
        return (root, query, cb) ->
            price == null ? null :
            cb.greaterThan(root.get("price"), price);
    }

    private static Specification<Product> ratingGreaterThan(Double rating) {
        return (root, query, cb) ->
            rating == null ? null :
            cb.greaterThan(root.get("rating"), rating);
    }
}
// END
