package retail.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerModel, Integer> {
    CustomerModel findByCH3ShipToCode(Integer CH3ShipToCode);

}
