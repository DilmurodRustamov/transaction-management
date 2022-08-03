package uz.upay.transactionmanagement.service;

import uz.upay.transactionmanagement.entity.Product;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.ProductDto;

import java.util.List;

public interface ProductService {

    ApiResponse addProduct(ProductDto productDto);

    Product getProduct(Long id);

    List<Product> getAllProducts();

    ApiResponse deleteProduct(Long productId);

    ApiResponse updateProduct(Long productId, ProductDto ProductDto);

}
