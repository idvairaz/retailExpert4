package retail.price;

import lombok.Data;

@Data
public class PriceDto {
    Integer id;
    String chainName;
    Integer materialNo;
    Double regularPricePerUnit;
}
