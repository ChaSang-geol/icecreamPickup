package icecreamPickup;

import icecreamPickup.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired
    private SalesRepository salesRepository;

    // view 객체 생성
    //private Sales storesales = new Sales();

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverIceCreamOrderCanceled_OrderCancelReq(@Payload IceCreamOrderCanceled iceCreamOrderCanceled){

        if(iceCreamOrderCanceled.isMe()){
            System.out.println("##### listener OrderCancelReq : " + iceCreamOrderCanceled.toJson());
            Sales cancelstoresales = new Sales();
            cancelstoresales = salesRepository.findByOrderId(iceCreamOrderCanceled.getId());
            cancelstoresales.setStatus("CANCEL");
            // 레파지 토리에 save
            salesRepository.save(cancelstoresales);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_StoreOrderAcceptReq(@Payload PaymentApproved paymentApproved){

        if(paymentApproved.isMe()){
            System.out.println("##### listener StoreOrderAcceptReq : " + paymentApproved.toJson());
            if ("APPROVED".equals(paymentApproved.getPaymentStatus())){

                try {
                    Sales storesales = new Sales();

                    //Sales storesales = salesRepository.findByOrderId(paymentApproved.getOrderId());

                    storesales.setOrderId(paymentApproved.getOrderId());
                    // 레파지 토리에 save
                    //storesales.setStoreId(Long.valueOf(1));
                    // 레파지 토리에 save

                    storesales.setStatus("WAITING");
                    salesRepository.save(storesales);

                } catch (Exception e) {
                    System.out.println("#####   Error : paymentApproved #########");
                    e.printStackTrace();

                }
            }
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenIceCreamOrdered_then_CREATE_ (@Payload IceCreamOrdered iceCreamOrdered) {
        try {
            if (iceCreamOrdered.isMe()) {
                Sales storesales = new Sales();
                storesales = salesRepository.findByOrderId(iceCreamOrdered.getId());
                // 이벤트의 Value 를 set 함
                if (storesales.getId() != null) {
                    //storesales.setOrderId(iceCreamOrdered.getId());
                    //storesales.setProductName(iceCreamOrdered.getProductName());
                    storesales.setStoreId(iceCreamOrdered.getStoreId());
                    //storesales.setCustomerName(iceCreamOrdered.getCustomerName());
                    //storesales.setStatus(iceCreamOrdered.getOrderStatus());
                    // view 레파지 토리에 save
                    salesRepository.save(storesales);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_StoreOrderReceived(@Payload StoreOrderReceived storeOrderReceived){

        if(storeOrderReceived.isMe()){
            System.out.println("##### listener OrderCancelReq : " + storeOrderReceived.toJson());
            Sales storesales = new Sales();
            storesales = salesRepository.findByOrderId(storeOrderReceived.getId());
            storesales.setStatus("ACCEPT");
            storesales.setStoreId(storeOrderReceived.getStoreId());
            // 레파지 토리에 save
            salesRepository.save(storesales);
        }
    }
    */

}
