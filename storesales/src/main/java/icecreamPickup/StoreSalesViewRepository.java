package icecreamPickup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreSalesViewRepository extends CrudRepository<StoreSalesView, Long> {

    List<StoreSalesView> findByOrderId(Long orderId);

}