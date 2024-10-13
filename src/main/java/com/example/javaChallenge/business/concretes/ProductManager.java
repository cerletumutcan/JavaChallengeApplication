package com.example.javaChallenge.business.concretes;

import com.example.javaChallenge.business.abstracts.ProductService;
import com.example.javaChallenge.core.utilities.results.*;
import com.example.javaChallenge.dataAccess.abstracts.ProductDao;
import com.example.javaChallenge.dtos.ProductDTO;
import com.example.javaChallenge.entities.concretes.Product;
import com.example.javaChallenge.mappers.ProductMapperImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao){
        super();
        this.productDao = productDao;
    }

    @Override
    public Result addProduct(ProductDTO productDTO) {
        Product product = ProductMapperImpl.toEntity(productDTO);
        this.productDao.save(product);
        return new SuccessResult("Ürün Başarıyla Eklendi...");
    }


    @Override
    public DataResult<List<ProductDTO>> getProduct() {
        List<Product> productList = productDao.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        productList.stream().forEach(product -> {
            productDTOList.add(ProductMapperImpl.toDTO(product));
        });

        return new SuccessDataResult<List<ProductDTO>>
                (productDTOList, "Ürünler Listelendi...");
    }

    @Override
    public Result updateProduct(ProductDTO productDTO) {
        Optional<Product> productOptional = productDao.findById(productDTO.getId());
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            Product updateProduct = ProductMapperImpl.toEntity(productDTO);
            productDao.save(updateProduct);
            return new SuccessResult("Ürün Başarıyla Güncellendi...");
        } else {
            return new ErrorResult("Ürün Güncellenemedi...");
        }

    }

    @Override
    public Result deleteProduct(int id) {
        boolean exists = productDao.existsById(id);
        if (exists){
            productDao.deleteById(id);
            return new SuccessResult("Ürün Başarıyla Silindi...");
        } else {
            return new ErrorResult("Ürün Silinemedi...");
        }
    }

    @Override
    @Transactional
    public Result updateStock(int id, int quantity) {

        Optional<Product> productOptional = productDao.findById(id);
        if (!productOptional.isPresent()){
            return new ErrorResult("Ürün bulunamadı...");
        }

        Product product = productOptional.get();

        int currentStock = product.getUnitsInStock();
        if (currentStock < quantity){
            return new ErrorResult("Yeterli sayıda ürün bulunamadı...");
        }

        product.setUnitsInStock(currentStock - quantity);

        productDao.save(product);

        return new SuccessResult("Stok başarıyla güncellendi...");
    }



























}
