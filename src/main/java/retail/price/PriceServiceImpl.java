package retail.price;

import org.springframework.stereotype.Service;
import retail.network.Network;
import retail.network.NetworkService;
import retail.product.Product;
import retail.product.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    final private PriceRepo priceRepo;
    final private ProductService productService;
    final private NetworkService networkService;

    public PriceServiceImpl(PriceRepo priceRepo, ProductService productService, NetworkService networkService) {

        this.priceRepo = priceRepo;
        this.productService = productService;
        this.networkService = networkService;
    }

    @Override
    public List<PriceDto> getPrices() {
        List<PriceModel> priceModelList = priceRepo.findAll();
        List<PriceDto> priceDtoList = new ArrayList<>();
        for(PriceModel priceModel : priceModelList){
            PriceDto priceDto = new PriceDto();
            priceDto.setId(priceModel.getId());
            priceDto.setChainName(priceModel.getNetwork().getNetworkName());
            priceDto.setRegularPricePerUnit(priceModel.getRegularPricePerUnit());
            priceDto.setMaterialNo(priceModel.getProduct().getMaterialNo());
            priceDtoList.add(priceDto);
        }
        return priceDtoList;
    }

    @Override
    public PriceDto getPriceDtoById(Integer idPrice) {
        PriceDto priceDto = new PriceDto();
        Optional<PriceModel> price = priceRepo.findById(idPrice);
        if (price.isEmpty()) {
            return null;
        }
        priceDto.setId(price.get().getId());
        priceDto.setMaterialNo(price.get().getProduct().getMaterialNo());
        priceDto.setRegularPricePerUnit(price.get().getRegularPricePerUnit());
        priceDto.setChainName(price.get().getNetwork().getNetworkName());
        return priceDto;
    }

    @Override
    public void add(PriceDto priceDto) {
        PriceModel priceModel = new PriceModel();
        priceModel.setNetwork(networkService.getNetworkByNetworkName(priceDto.getChainName()));
        priceModel.setRegularPricePerUnit(priceDto.getRegularPricePerUnit());
        priceModel.setProduct(productService.getProductByMaterialNo(priceDto.getMaterialNo()));
        priceRepo.save(priceModel);
    }

    @Override
    public void edit(PriceDto priceDto) {
        PriceModel priceModel = priceRepo.getReferenceById(priceDto.getId());
        priceModel.setNetwork(networkService.getNetworkByNetworkName(priceDto.getChainName()));
        priceModel.setRegularPricePerUnit(priceDto.getRegularPricePerUnit());
        priceModel.setProduct(productService.getProductByMaterialNo(priceDto.getMaterialNo()));
        priceRepo.save(priceModel);
    }

    @Override
    public void deletePriceById(Integer idPrice) {
        priceRepo.deleteById(idPrice);

    }

    @Override
    public PriceModel getPriceByProductAndNetwork(Product product, Network network) {
        return priceRepo.findByProductAndNetwork(product, network);
    }
}
