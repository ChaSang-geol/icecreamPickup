package icecreamPickup;

import icecreamPickup.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StoreSalesViewViewHandler {


    @Autowired
    private StoreSalesViewRepository storeSalesViewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenIceCreamOrdered_then_CREATE_ (@Payload IceCreamOrdered iceCreamOrdered) {
        try {
            if (iceCreamOrdered.isMe()) {
                // view 객체 생성
                StoreSalesView storeSalesView = new StoreSalesView();
                // view 객체에 이벤트의 Value 를 set 함
                storeSalesView.setOrderId(iceCreamOrdered.getId());
                storeSalesView.setProductName(iceCreamOrdered.getProductName());
                storeSalesView.setStoreId(iceCreamOrdered.getStoreId());
                storeSalesView.setCustomerName(iceCreamOrdered.getCustomerName());
                storeSalesView.setStatus(iceCreamOrdered.getOrderStatus());
                // view 레파지 토리에 save
                storeSalesViewRepository.save(storeSalesView);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenIceCreamOrderCanceled_then_UPDATE_(@Payload IceCreamOrderCanceled iceCreamOrderCanceled) {
        try {
            if (iceCreamOrderCanceled.isMe()) {
                // view 객체 조회
                List<StoreSalesView> storeSalesViewList = storeSalesViewRepository.findByOrderId(iceCreamOrderCanceled.getId());
                for(StoreSalesView storeSalesView : storeSalesViewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    storeSalesView.setStatus(iceCreamOrderCanceled.getOrderStatus());
                    // view 레파지 토리에 save
                    storeSalesViewRepository.save(storeSalesView);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPickedUp_then_UPDATE_(@Payload PickedUp pickedUp) {
        try {
            if (pickedUp.isMe()) {
                // view 객체 조회
                List<StoreSalesView> storeSalesViewList = storeSalesViewRepository.findByOrderId(pickedUp.getOrderId());
                for(StoreSalesView storeSalesView : storeSalesViewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    storeSalesView.setStatus(pickedUp.getStatus());
                    // view 레파지 토리에 save
                    storeSalesViewRepository.save(storeSalesView);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPacked_then_UPDATE_(@Payload Packed packed) {
        try {
            if (packed.isMe()) {
                // view 객체 조회
                List<StoreSalesView> storeSalesViewList = storeSalesViewRepository.findByOrderId(packed.getOrderId());
                for(StoreSalesView storeSalesView : storeSalesViewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    storeSalesView.setStatus(packed.getStatus());
                    // view 레파지 토리에 save
                    storeSalesViewRepository.save(storeSalesView);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCanceled_then_UPDATE_(@Payload OrderCanceled orderCanceled) {
        try {
            if (orderCanceled.isMe()) {
                // view 객체 조회
                List<StoreSalesView> storeSalesViewList = storeSalesViewRepository.findByOrderId(orderCanceled.getOrderId());
                for(StoreSalesView storeSalesView : storeSalesViewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    storeSalesView.setStatus(orderCanceled.getStatus());
                    // view 레파지 토리에 save
                    storeSalesViewRepository.save(storeSalesView);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentApproved_then_UPDATE_(@Payload PaymentApproved paymentApproved) {
        try {
            if (paymentApproved.isMe()) {
                // view 객체 조회
                List<StoreSalesView> storeSalesViewList = storeSalesViewRepository.findByOrderId(paymentApproved.getOrderId());
                for(StoreSalesView storeSalesView : storeSalesViewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    storeSalesView.setPaymentStatus(paymentApproved.getPaymentStatus());
                    // view 레파지 토리에 save
                    storeSalesViewRepository.save(storeSalesView);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCanceled_then_UPDATE_(@Payload PaymentCanceled paymentCanceled) {
        try {
            if (paymentCanceled.isMe()) {
                // view 객체 조회
                List<StoreSalesView> storeSalesViewList = storeSalesViewRepository.findByOrderId(paymentCanceled.getOrderId());
                for(StoreSalesView storeSalesView : storeSalesViewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    storeSalesView.setPaymentStatus(paymentCanceled.getPaymentStatus());
                    // view 레파지 토리에 save
                    storeSalesViewRepository.save(storeSalesView);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenStoreOrderReceived_then_UPDATE_(@Payload StoreOrderReceived storeOrderReceived) {
        try {
            if (storeOrderReceived.isMe()) {
                // view 객체 조회
                List<StoreSalesView> storeSalesViewList = storeSalesViewRepository.findByOrderId(storeOrderReceived.getOrderId());
                for(StoreSalesView storeSalesView : storeSalesViewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    storeSalesView.setStatus(storeOrderReceived.getStatus());
                    // view 레파지 토리에 save
                    storeSalesViewRepository.save(storeSalesView);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}