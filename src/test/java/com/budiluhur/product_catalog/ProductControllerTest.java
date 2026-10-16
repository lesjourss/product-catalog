package com.budiluhur.product_catalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));
    }

    @Test
    void testGetProductByIdSuccess() throws Exception {
        mockMvc.perform(get("/api/products/PRD-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("PRD-01"))
                .andExpect(jsonPath("$.name").value("Keyboard Mechanical"));
    }

    @Test
    void testGetProductByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/products/PRD-99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("tidak ditemukan")));
    }

    @Test
    void testCreateProduct() throws Exception {
        String jsonPayload = """
                {
                    "id": "PRD-04",
                    "name": "Headset Gaming",
                    "price": 1000000.0
                }
                """;

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isCreated())
                .andExpect(content().string("Produk 'Headset Gaming' berhasil ditambahkan!"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        String updatePayload = """
                {
                    "name": "Keyboard Mechanical RGB",
                    "price": 550000.0
                }
                """;

        mockMvc.perform(put("/api/products/PRD-01")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatePayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("PRD-01"))
                .andExpect(jsonPath("$.name").value("Keyboard Mechanical RGB"))
                .andExpect(jsonPath("$.price").value(550000.0));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/PRD-02"))
                .andExpect(status().isOk())
                .andExpect(content().string("Produk dengan ID 'PRD-02' berhasil dihapus!"));

        // Verifikasi sudah dihapus
        mockMvc.perform(get("/api/products/PRD-02"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSearchProductMiniChallenge() throws Exception {
        // Mini Challenge: GET /api/products/search?keyword=keyboard
        mockMvc.perform(get("/api/products/search")
                        .param("keyword", "keyboard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].name", containsStringIgnoringCase("keyboard")));
    }
}
