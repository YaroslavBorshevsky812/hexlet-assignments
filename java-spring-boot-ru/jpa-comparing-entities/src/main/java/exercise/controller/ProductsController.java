package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.exception.ResourceAlreadyExistsException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        // 1. Проверяем, что product не null
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product data cannot be null");
        }

        // 2. Проверяем обязательные поля
        if (product.getTitle() == null || product.getTitle().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product title cannot be empty");
        }

        if (product.getPrice() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be positive");
        }

        // 3. Проверяем существование товара (только после валидации полей)
        List<Product> existingProducts = productRepository.findAll();
        for (Product existing : existingProducts) {
            if (existing.getTitle() != null &&
                existing.getTitle().equalsIgnoreCase(product.getTitle())
                && existing.getPrice() == product.getPrice()) {
                throw new ResourceAlreadyExistsException(
                    "Product with title '" + product.getTitle() +
                        "' and price " + product.getPrice() + " already exists");
            }
        }

        // 4. Сохраняем, если все проверки пройдены
        return productRepository.save(product);
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {
        var product = productRepository.findById(id)
                                       .orElseThrow(
                                           () -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }

    @PutMapping(path = "/{id}")
    public Product update(@PathVariable long id, @RequestBody Product productData) {
        var product = productRepository.findById(id)
                                       .orElseThrow(
                                           () -> new ResourceNotFoundException("Product with id " + id + " not found"));

        product.setTitle(productData.getTitle());
        product.setPrice(productData.getPrice());

        productRepository.save(product);

        return product;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
