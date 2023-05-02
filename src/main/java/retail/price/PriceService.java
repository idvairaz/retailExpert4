package retail.price;

import retail.network.Network;
import retail.product.Product;

import java.util.List;

public interface PriceService {
    List<PriceDto> getPrices();
    PriceDto getPriceDtoById(Integer idPrice);
    void add(PriceDto priceDto);
    void edit(PriceDto priceDto);
    void  deletePriceById(Integer idPrice);
    PriceModel getPriceByProductAndNetwork(Product product, Network network);
}
