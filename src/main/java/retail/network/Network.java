package retail.network;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="networks")
public class Network {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "network_Name")
    private String networkName;
}
