package icecreamPickup;

import icecreamPickup.config.kafka.KafkaProcessor;
import icecreamPickup.Order;
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
    private OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverStoreOrderReceived_OrderStatusUpdate(@Payload StoreOrderReceived storeOrderReceived){

        if(storeOrderReceived.isMe()){
            System.out.println("##### listener OrderStatusUpdate : " + storeOrderReceived.toJson());
           Optional<Order> icecreamorders = orderRepository.findById(storeOrderReceived.getOrderId());
            //Order icecreamorders = new Order();
            icecreamorders.get().setOrderStatus(storeOrderReceived.getStatus());
            // 레파지 토리에 save
            orderRepository.save(icecreamorders.get());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPacked_OrderStatusUpdate(@Payload Packed packed){

        if(packed.isMe()){
            System.out.println("##### listener OrderStatusUpdate : " + packed.toJson());
            //Order icecreamorders = new Order();
            //icecreamorders.setOrderStatus("READY");
            Optional<Order> icecreamorders = orderRepository.findById(packed.getOrderId());
            icecreamorders.get().setOrderStatus(packed.getStatus());
            // 레파지 토리에 save
            orderRepository.save(icecreamorders.get());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPickedUp_OrderStatusUpdate(@Payload PickedUp pickedUp){

        if(pickedUp.isMe()){
            System.out.println("##### listener OrderStatusUpdate : " + pickedUp.toJson());

            Optional<Order> icecreamorders = orderRepository.findById(pickedUp.getOrderId());
            icecreamorders.get().setOrderStatus(pickedUp.getStatus());
            //icecreamorders.setOrderStatus("COMPLETE");
            // 레파지 토리에 save
            orderRepository.save(icecreamorders.get());
        }
    }

}
