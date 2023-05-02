package retail.actual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import retail.network.Network;
import retail.product.Product;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    ActualService actualService;

    @Autowired
    public AnalysisController(ActualService actualService) {
        this.actualService = actualService;
    }

    /**
     * Метод для  выгрузки фактов продаж с учётом признака промо.
     *
     * @return справочные поля:  сеть, категория, месяц, факт продаж в штуках по базовой цене, факт продаж по промо цене,
     * доля продаж по промо, %.
     */

    @GetMapping("/unloadAccountingPromOrReg")
    public List<ActualDto2> unloadActuals() {
        return actualService.unloadAccountingPromOrReg();
    }

    /**
     * Метод для  выгрузки фактов продаж по дням, согласно фильтрации по списку наименований сетей и списку продуктов
     *
     * @param date     дата за которую нужна выгрузка в формате YYYY-mm-dd
     * @param networks список имен сетей через запятую в строковом формате
     * @param products список продуктов в виде кода "materialNo" через запятую в числовом формате
     * @return всех совпадающих фактов продаж на данную дату согласно фильтрам
     * пример запроса:
     * localhost:8080/analysis/filter?date=2020-12-28&networks=Chain%202,Chain%201&products=70158202,70149001
     */

    @GetMapping("/filter")
    private List<ActualDto> getAllActualByDate(@RequestParam (value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                               @RequestParam List<Network> networks,
                                               @RequestParam List<Product> products) {
        return actualService.getActualDtosByDateAndNetworksAndProducts(date, networks, products);
    }

    @GetMapping("/all")
    private List<ActualDto> getAllActual() {
        return actualService.getAllActuals();
    }

    @PostMapping()
    public void add(@RequestBody ActualDto actualDto) {
        actualService.add(actualDto);
    }
}




