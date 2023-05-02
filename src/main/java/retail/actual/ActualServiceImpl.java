package retail.actual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.customer.CustomerService;

import retail.network.Network;
import retail.network.NetworkService;
import retail.price.PriceModel;
import retail.product.Product;
import retail.price.PriceService;
import retail.product.ProductService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ActualServiceImpl implements ActualService {

    final private ActualRepo actualRepo;
    final private ProductService productService;
    final private CustomerService customerService;
    final private PriceService priceService;
    final private NetworkService networkService;


    @Autowired
    public ActualServiceImpl(ActualRepo actualRepo, ProductService productService, CustomerService customerService, PriceService priceService, NetworkService networkService) {
        this.actualRepo = actualRepo;
        this.productService = productService;
        this.customerService = customerService;
        this.priceService = priceService;
        this.networkService = networkService;
    }

    @Override
    public void add(ActualDto actualDto) {

        ActualModel actualModel = new ActualModel();

        actualModel.setDate(actualDto.getDate());
        actualModel.setProduct(productService.getProductByMaterialNo(actualDto.getMaterialNo()));
        actualModel.setCustomerModel(customerService.getByCH3ShipToCode(actualDto.getCh3ShipToCode()));
        actualModel.setVolume(actualDto.getVolume());
        actualModel.setActualSalesValue(actualDto.getActualSalesValue());

        Product product = productService.getProductByMaterialNo(actualDto.getMaterialNo());
        Network network = networkService.getNetworkByNetworkName(actualDto.getNetworkName());
        PriceModel priceModel = priceService.getPriceByProductAndNetwork(product, network);

        actualModel.setNetwork(network);
        actualModel.setPromoOrRegularMarker(
                (actualDto.getActualSalesValue() / actualDto.getVolume() == priceModel.getRegularPricePerUnit())
                        ? "Regular"
                        : "Promo"
        );
        actualRepo.save(actualModel);
    }

    @Override
    public List<ActualDto2> unloadAccountingPromOrReg() {
        List<Object>  list = actualRepo.unloadAccountingPromOrReg();
        Iterator it = list.iterator();
        List<ActualDto2> actualDto2List = new ArrayList<>();
        while (it.hasNext()){
            int i = 0;
            Object[] row = (Object[]) it.next();
            ActualDto2 actualDto2 = new ActualDto2();
            actualDto2.setNetworkName(String.valueOf(row[i++]));
            actualDto2.setL3ProductCategoryName(String.valueOf(row[i++]));
            actualDto2.setMonth((Timestamp) row[i++]);
            actualDto2.setValueRegular((Integer) row[i++]);
            actualDto2.setValuePromo((Integer) row[i++]);
            actualDto2.setPartPromo((Double) row[i++]);
            actualDto2.setPercentPromo((Double) row[i++]);
            actualDto2List.add(actualDto2);
        }
        return actualDto2List;
    }

    @Override
    public List<ActualDto> getAllActuals() {
        return helperMethod(actualRepo.findAll());
    }
    @Override
    public List<ActualDto> getActualDtosByDateAndNetworksAndProducts(LocalDate date, List<Network> networks, List<Product> products){

        return helperMethod(actualRepo.findAllByDateAndNetworkIsInAndProductIsIn(date, networks, products));
    }
    public List<ActualDto> helperMethod(List <ActualModel> actualModelList) {

        List<ActualDto> actualDtoList = new ArrayList<>();
        for (ActualModel actualModel : actualModelList){
            ActualDto actualDto = new ActualDto();
            actualDto.setId(actualModel.getId());
            actualDto.setDate(actualModel.getDate());
            actualDto.setMaterialNo(actualModel.getProduct().getMaterialNo());
            actualDto.setActualSalesValue(actualModel.getActualSalesValue());
            actualDto.setNetworkName(actualModel.getNetwork().getNetworkName());
            actualDto.setCh3ShipToCode(actualModel.getCustomerModel().getCH3ShipToCode());
            actualDto.setVolume(actualModel.getVolume());
            actualDtoList.add(actualDto);
        }
        return actualDtoList;
    }
}


