package retail.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/allCustomers")
    private List<CustomerModel> allCustomer(){
        return customerService.getAllCustomers();
    }
    @PostMapping
    public CustomerModel add (@RequestBody CustomerModel customerModel){
        return customerService.add(customerModel);
    }

    @PutMapping
    public CustomerModel edit (@RequestBody CustomerModel customerModel){
        return customerService.edit(customerModel);
    }

    @DeleteMapping("/id")
    private void deleteCustomerById (@RequestParam Integer id_customer){
        customerService.deleteCustomerById(id_customer);
    }
    @GetMapping("/shipToCode")
    private CustomerModel getByCH3ShipToCode(@RequestParam Integer shipToCode){
        return  customerService.getByCH3ShipToCode(shipToCode);
    }
}
