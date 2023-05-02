package retail.price;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import retail.network.Network;
import retail.product.Product;

import javax.persistence.*;


@Data
@Entity
@Table(name = "price")
public class PriceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_price")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "network_Name", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Network network;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_No", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Product product; //int materialNo; in Product


    @Column(name = "regular_PricePer_Unit")
    private double regularPricePerUnit;
}
