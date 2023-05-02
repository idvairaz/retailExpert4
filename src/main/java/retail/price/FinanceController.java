package retail.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finance/price")
public class FinanceController {
    private final PriceService priceService;

    @Autowired
    public FinanceController(PriceService priceService) {
        this.priceService = priceService;
    }


    @GetMapping("/all")
    private List<PriceDto> getPrices(){ return priceService.getPrices(); }

    @PostMapping("/add")
    public void add(@RequestBody PriceDto priceDto){ priceService.add(priceDto); }

    @PutMapping
    public void edit(@RequestBody PriceDto priceDto){
        priceService.edit(priceDto);
    }

    @DeleteMapping("/delete")
    private void deletePriceById(@RequestParam Integer id){
        priceService.deletePriceById(id);
    }
}
