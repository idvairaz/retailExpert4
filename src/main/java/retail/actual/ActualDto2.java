package retail.actual;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ActualDto2 {

    String networkName;
    String l3ProductCategoryName;
    Timestamp month;
    int valueRegular;
    int valuePromo;
    double partPromo;
    double percentPromo;
}
