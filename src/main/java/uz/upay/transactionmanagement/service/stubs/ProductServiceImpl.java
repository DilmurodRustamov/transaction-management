package uz.upay.transactionmanagement.service.stubs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.upay.transactionmanagement.entity.District;
import uz.upay.transactionmanagement.entity.Product;
import uz.upay.transactionmanagement.exeptions.NotFoundException;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.DistrictDto;
import uz.upay.transactionmanagement.payload.ProductDto;
import uz.upay.transactionmanagement.repository.DistrictRepository;
import uz.upay.transactionmanagement.repository.ProductRepository;
import uz.upay.transactionmanagement.repository.RegionRepository;
import uz.upay.transactionmanagement.service.DistrictService;
import uz.upay.transactionmanagement.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static uz.upay.transactionmanagement.apiResponsdeMessages.ResponseMessageKeys.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ApiResponse addProduct(ProductDto productDto) {
        boolean existsByName = productRepository.existsByName(productDto.getName());
        if (existsByName)
            return new ApiResponse(PRODUCT_ALREADY_EXISTS, false);
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        productRepository.save(product);
        return new ApiResponse(PRODUCT_SAVED, true);
    }

    @Override
    public Product getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException(RECORD_NOT_FOUND));
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public ApiResponse deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        return new ApiResponse(PRODUCT_DELETED, true);
    }

    @Transactional
    @Override
    public ApiResponse updateProduct(Long productId, ProductDto productDto) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new NotFoundException(RECORD_NOT_FOUND));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        productRepository.save(product);
        return new ApiResponse(PRODUCT_UPDATED, true);
    }
}
