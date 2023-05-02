package retail.actual;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import retail.network.Network;
import retail.product.Product;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActualRepo extends JpaRepository<ActualModel, Integer> {

    @Query(value = "SELECT  a.network_name, p.l3product_category_name, DATE_TRUNC('month', a.date) as month,\n" +
            "  SUM(CASE WHEN a.promo_or_regular_marker = 'Regular' THEN a.volume ELSE 0 END)\\:\\:integer as value_regular,\n" +
            "  SUM(CASE WHEN a.promo_or_regular_marker = 'Promo' THEN a.volume ELSE 0 END)\\:\\:integer as value_promo,\n" +
            "  ROUND(SUM(CASE WHEN a.promo_or_regular_marker = 'Promo' THEN a.volume ELSE 0.00 END)/sum(a.volume),2)\\:\\:double precision as part_promo,\n" +
            "  ROUND(SUM(CASE WHEN a.promo_or_regular_marker = 'Promo' THEN a.volume ELSE 0.00 END)/sum(a.volume),2)\\:\\:double precision * 100 as percent_promo\n" +
            "  FROM actuals a join products p  on a.material_no = p.material_no\n" +
            "  GROUP BY \n" +
            "  a.network_name,\n" +
            "  p.l3product_category_name,\n" +
            "  DATE_TRUNC('month', a.date)", nativeQuery = true)
    List<Object> unloadAccountingPromOrReg();

    List<ActualModel> findAllByDateAndNetworkIsInAndProductIsIn(LocalDate date, List<Network> networks, List<Product> products);

}




