package retail.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retail.network.Network;
import retail.product.Product;


@Repository
public interface PriceRepo extends JpaRepository<PriceModel, Integer> {
    PriceModel findByProductAndNetwork(Product product, Network network);
}
