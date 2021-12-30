package com.mercadolivre.desafiospring1.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolivre.desafiospring1.entities.Product;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<>();
    private final String PATH = "src/main/resources/json/products.json";
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public List<Product> findAllProducts() throws IOException {
        File file = new File(PATH);
        FileInputStream is = new FileInputStream(file);
        products = Arrays.asList(objectMapper.readValue(is, Product[].class));
        return products;
    }

    public void saveProduct(Product product) throws IOException {
        findAllProducts();
        List<Product> listProducts = new ArrayList<>(products);
        product.setProductId((long) (listProducts.size()+1));
        listProducts.add(product);

        objectMapper.writeValue(new File(PATH), listProducts);
    }
}
