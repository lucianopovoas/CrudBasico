package org.example.controller;

import org.example.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private List<Product> productList = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productList;
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product) {
        product.setId(idCounter++);
        productList.add(product);
        return product;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product productToUpdate = getProductById(id);
        if (productToUpdate != null) {
            productToUpdate.setName(updatedProduct.getName());
            productToUpdate.setPrice(updatedProduct.getPrice());
        }
        return productToUpdate;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productList.removeIf(product -> product.getId().equals(id));
    }
}
