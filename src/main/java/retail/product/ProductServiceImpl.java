package retail.product;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepo productRepo;


    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product getProductByMaterialNo(Integer materialNo) {
        return productRepo.findProductByMaterialNo(materialNo);
    }
    @Override
    public ProductDto getProductDtoByMaterialNo(Integer materialNo) {
        ProductDto productDto = new ProductDto();
        Optional<Product> product = productRepo.findById(materialNo);
        if(product.isEmpty()){
            return null;
        }
        productDto.setMaterialNo(product.get().getMaterialNo());
        productDto.setL3ProductCategoryName(product.get().getL3ProductCategoryName());
        productDto.setL3ProductCategoryCode(product.get().getL3ProductCategoryCode());
        productDto.setMaterialDescRus(product.get().getMaterialDescRus());
        return productDto;
    }

}


