package icecreamPickup;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PaymentInformationRepository extends PagingAndSortingRepository<PaymentInformation, Long>{
    PaymentInformation findByOrderId(Long orderId);

}