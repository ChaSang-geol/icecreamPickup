
package icecreamPickup.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

//@FeignClient(name="pay", url="http://pay:8080")
@FeignClient(name="pay", url="http://localhost:8083")
public interface PaymentInformationService {

    @RequestMapping(method= RequestMethod.POST, path="/paymentInformations")
    public void payment(@RequestBody PaymentInformation paymentInformation);

}