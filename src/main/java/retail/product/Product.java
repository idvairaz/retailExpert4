package retail.product;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "material_No")
    private Integer materialNo;

    @Column(name = "material_Desc_Rus")
    private String  materialDescRus;

    @Column(name = "L3Product_Category_Code")
    private int L3ProductCategoryCode;

    @Column(name = "L3Product_Category_Name")
    private String  L3ProductCategoryName;
}
