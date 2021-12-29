package com.mercadolivre.desafiospring1.services;

import com.mercadolivre.desafiospring1.entities.Product;
import com.mercadolivre.desafiospring1.exception.RepositoryException;
import com.mercadolivre.desafiospring1.repositories.ProductRepository;
import com.mercadolivre.desafiospring1.util.Is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
   private ProductRepository productRepository;

    public List<Product> findAllProducts() {
        List<Product> products = null;
        try {
            products = productRepository.findAllProducts();
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
        return products;
    }

    public List<Product> getProductByAnyQuery(Map<String, String> requestParams) {

        List<Product> productList = findAllProducts();

        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            if (!entry.getValue().equals(null)) {
                productList = productList.stream().filter(product -> {
                    String valor = requestParams.get(entry.getKey());

                    if(Is.isNumericException(valor) && product.getPrice().compareTo(new BigDecimal(String.valueOf(valor))) <= 0)
                        return true;
                    if(Is.isBooleanException(valor) && product.isFreeShipping() == Boolean.parseBoolean(valor))
                        return true;
                    if (product.getCategory().equalsIgnoreCase(valor))
                        return true;
                    if (product.getBrand().equalsIgnoreCase(valor))
                        return true;
                    if (product.getName().equalsIgnoreCase(valor))
                        return true;
                    if (product.getPrestige().equalsIgnoreCase(valor))
                        return true;

                    return false;

                }).collect(Collectors.toList());
            }
        }

        return productList;
    }
}
