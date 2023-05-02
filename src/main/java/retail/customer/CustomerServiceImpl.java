package retail.customer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    final private CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }
    @Override
    public List<CustomerModel> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public CustomerModel add(CustomerModel customerModel) {
        return customerRepo.save(customerModel);
    }

    @Override
    public CustomerModel edit(CustomerModel customerModel) {return customerRepo.save(customerModel);
    }
    @Override
    public  void  deleteCustomerById(Integer id_customer) {
        customerRepo.deleteById(id_customer);
    }

    @Override
    public CustomerModel getByCH3ShipToCode(Integer CH3ShipToCode) {
        return customerRepo.findByCH3ShipToCode(CH3ShipToCode);
    }

}
