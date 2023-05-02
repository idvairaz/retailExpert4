package retail.actual;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ActualDto {
    Integer id;
    LocalDate date;
    Integer materialNo;
    Integer ch3ShipToCode;
    String networkName;
    int volume;
    double actualSalesValue;
}
