package org.frisbeemall.controller;

import org.frisbeemall.domain.Product;
import org.frisbeemall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/show")
    public Result<List<Product>> getCurrentProduct(){
        List<Product> products = productService.getCurrentProduct();
        return Result.success(products);
    }

    @PostMapping("/add")
    public ResponseEntity<Result<String>> addProduct(
            @RequestParam("productName") String productName,
            @RequestParam("productDescription") String productDescription,
            @RequestParam("productPrice") String productPrice,
            @RequestParam("productImage") MultipartFile productImage) {

        // 检查文件是否为空
        if (productImage.isEmpty()) {
            return ResponseEntity.badRequest().body(Result.error("图片上传失败，文件为空"));
        }

        // 生成唯一文件名
        String fileName = UUID.randomUUID().toString() + "_" + productImage.getOriginalFilename();

        // 定义图片保存的路径（你可以将其改为你的具体路径）
        String uploadDir = System.getProperty("user.dir") + "/image/";

// 检查目录是否存在，不存在则创建
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();  // 创建目录
        }
        File file = new File(uploadDir, fileName);

        try {
            // 保存文件到服务器
            productImage.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Result.error("图片上传失败，服务器错误"));
        }

        // 构造图片访问的 URL
        String imageUrl = "/image/" + fileName;

        // 调用业务层保存商品信息
        productService.addProduct(productName, productDescription, productPrice, imageUrl);

        return ResponseEntity.ok(Result.success("商品添加成功"));
    }


    @GetMapping("/history")
    public ResponseEntity<Result<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(Result.success(products));
    }



}
