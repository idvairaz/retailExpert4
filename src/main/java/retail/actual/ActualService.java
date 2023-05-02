package retail.actual;

import retail.network.Network;
import retail.product.Product;

import java.time.LocalDate;
import java.util.List;

public interface ActualService {

    List<ActualDto> getActualDtosByDateAndNetworksAndProducts(LocalDate date, List<Network> networks, List<Product> products);
    List<ActualDto> getAllActuals();
    void add(ActualDto actualDto);
    List<ActualDto2> unloadAccountingPromOrReg();


}




