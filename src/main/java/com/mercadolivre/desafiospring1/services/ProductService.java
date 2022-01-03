package com.mercadolivre.desafiospring1.services;

import com.mercadolivre.desafiospring1.dtos.ArticleDTO;
import com.mercadolivre.desafiospring1.dtos.ProductDTO;
import com.mercadolivre.desafiospring1.entities.Article;
import com.mercadolivre.desafiospring1.entities.Product;
import com.mercadolivre.desafiospring1.exception.RepositoryException;
import com.mercadolivre.desafiospring1.repositories.ProductRepository;
import com.mercadolivre.desafiospring1.util.Is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts() {
        List<Product> products;

        try {
            products = productRepository.findAllProducts();
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
        return products;
    }

    public List<Product> findAllProductsAvaliable() {
        return findAllProducts().stream()
                .filter(p -> p.getQuantity() > 0)
                .collect(Collectors.toList());
    }

    public List<Product> getProductByAnyQuery(Map<String, String> requestParams) {

        List<Product> productList = findAllProducts();
        String order = requestParams.getOrDefault("order", "");
        requestParams.remove("order");

        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            if (entry.getValue() != null) {
                productList = productList.stream().filter(product -> {
                    String valor = requestParams.get(entry.getKey());

                    if (Is.isNumericException(valor) && product.getPrice().compareTo(new BigDecimal(String.valueOf(valor))) <= 0)
                        return true;
                    if (Is.isBooleanException(valor) && product.isFreeShipping() == Boolean.parseBoolean(valor))
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

        sortByAnyParam(productList, order);

        return productList;
    }

    public List<Product> sortByAnyParam(List<Product> listProduct, String param) {

        switch (param) {
            case "0":
                listProduct.sort((p1, p2) -> (p1.getName().compareTo(p2.getName())));
                break;
            case "1":
                listProduct.sort((p1, p2) -> (p2.getName().compareTo(p1.getName())));
                break;
            case "2":
                listProduct.sort((p1, p2) -> (p1.getPrice().compareTo(p2.getPrice())));
                break;
            case "3":
                listProduct.sort((p1, p2) -> (p2.getPrice().compareTo(p1.getPrice())));
                break;
        }

        return listProduct;
    }

    public ArticleDTO saveProducts(Article article) {
        try {
            List<Product> productDb = productRepository.findAllProducts();
            List<Product> productsArticles = article.getArticles();

            for (Product p : productsArticles) {

                if (!productDb.contains(p)) {
                    productRepository.saveProduct(p);
                } else {
                    throw new RepositoryException("Product already exists " + p.getName());
                }
            }

        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }

        ArticleDTO dto = new ArticleDTO();
        article.getArticles().forEach(product -> dto.getArticlesDTO().add(ProductDTO.convert(product)));
        return dto;
    }

    public List<Product> filterProducts(Map.Entry<String, String> entry) {


        List<Product> productList = findAllProducts();

        if (entry.getValue() != null) {
            productList = productList.stream().filter(product -> {
                String valor = entry.getValue();

                if (Is.isNumericException(valor) && product.getPrice().compareTo(new BigDecimal(String.valueOf(valor))) <= 0)
                    return true;
                if (Is.isBooleanException(valor) && product.isFreeShipping() == Boolean.parseBoolean(valor))
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

        return productList;
    }
}