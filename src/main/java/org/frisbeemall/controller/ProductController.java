package org.frisbeemall.controller;

import org.frisbeemall.domain.Product;
import org.frisbeemall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ResponseEntity<Result<Product>> getCurrentProduct(){
        Product product = productService.getCurrentProduct();
        return ResponseEntity.ok(Result.success(product));
    }

    @PostMapping
    public ResponseEntity<Result<Product>> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok(Result.success(product));
    }

    @GetMapping("/history")
    public ResponseEntity<Result<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(Result.success(products));
    }

    @PostMapping("/freeze/{id}")
    public ResponseEntity<Result<String>> freezeProduct(@PathVariable Long id) {
        productService.freezeProduct(id);
        return ResponseEntity.ok(Result.success("冻结商品成功"));
    }

    @PostMapping("/unfreeze/{id}")
    public ResponseEntity<Result<String>> unfreezeProduct(@PathVariable Long id) {
        productService.unfreezeProduct(id);
        return ResponseEntity.ok(Result.success("解冻商品成功"));
    }

    @PostMapping("/sell/{id}")
    public ResponseEntity<Result<String>> sellProduct(@PathVariable Long id) {
        productService.sellProduct(id);
        return ResponseEntity.ok(Result.success("售出商品成功"));
    }


}
