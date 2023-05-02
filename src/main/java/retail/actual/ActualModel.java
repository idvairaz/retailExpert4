package retail.actual;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import retail.customer.CustomerModel;
import retail.network.Network;
import retail.product.Product;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="actuals")
public class ActualModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_actual")
    private int id;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_No", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Product product; //int materialNo; in Product

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CH3Ship_To_Code", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private CustomerModel customerModel;//int CH3ShipToCode; in Customers

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "network_Name", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Network network;//String chainName; in Network (networkName)

    @Column(name = "volume")
    private int volume;

    @Column(name = "actual_Sales_Value")
    private double actualSalesValue;

    @Column(name = "promo_Or_Regular_Marker")
    private String promoOrRegularMarker;
}
