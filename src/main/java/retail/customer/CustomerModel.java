package retail.customer;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="customers")
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_customer")
    private int id;

    @Column(name = "CH3Ship_To_Code")
    private int CH3ShipToCode;

    @Column(name = "CH3Ship_To_Name")
    private String CH3ShipToName;


    @Column(name = "chain_Name")
    private String chainName;
}
