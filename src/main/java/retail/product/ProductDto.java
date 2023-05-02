package retail.product;

import lombok.Data;


@Data
public class ProductDto {
    Integer materialNo;
    private String  materialDescRus;
    private int L3ProductCategoryCode;
    private String  L3ProductCategoryName;
}
