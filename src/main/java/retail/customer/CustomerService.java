package retail.customer;

import java.util.List;

public interface CustomerService {
    List<CustomerModel> getAllCustomers();
    CustomerModel add(CustomerModel customerModel);
    CustomerModel edit(CustomerModel customerModel);
    void  deleteCustomerById(Integer idCustomer);
    CustomerModel getByCH3ShipToCode(Integer CH3ShipToCode);
}
