package org.frisbeemall.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.frisbeemall.FrisbeeMallApplication;
import org.frisbeemall.domain.Product;
import org.frisbeemall.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = FrisbeeMallApplication.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    private Product testProduct;

    @BeforeEach
    public void setUp() {
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
    }

    @Test
    public void testAddProduct() throws Exception {
        when(productService.addProduct(any())).thenReturn(testProduct.getStatus());

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Product\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Test Product"));

        verify(productService).addProduct(any());
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> mockProducts = new ArrayList<>();
        // 添加测试产品到 mockProducts 列表
        when(productService.getAllProducts()).thenReturn(mockProducts); // 确保这里使用的是 mock 对象

        // 然后执行你的测试
        mockMvc.perform(get("/product/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    public void testFreezeProduct() throws Exception {
        mockMvc.perform(post("/product/freeze/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("冻结商品成功"));

        verify(productService).freezeProduct(1L);
    }

    @Test
    public void testUnfreezeProduct() throws Exception {
        mockMvc.perform(post("/product/unfreeze/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("解冻商品成功"));

        verify(productService).unfreezeProduct(1L);
    }

    @Test
    public void testSellProduct() throws Exception {
        mockMvc.perform(post("/product/sell/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("售出商品成功"));

        verify(productService).sellProduct(1L);
    }
}
