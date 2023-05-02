package retail.product;

public interface ProductService {
    Product getProductByMaterialNo(Integer materialNo);
    ProductDto getProductDtoByMaterialNo(Integer materialNo);

}
