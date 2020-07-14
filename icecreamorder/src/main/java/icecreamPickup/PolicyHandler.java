package icecreamPickup;

import icecreamPickup.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverStoreOrderReceived_OrderStatusUpdate(@Payload StoreOrderReceived storeOrderReceived){

        if(storeOrderReceived.isMe()){
            System.out.println("##### listener OrderStatusUpdate : " + storeOrderReceived.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPacked_OrderStatusUpdate(@Payload Packed packed){

        if(packed.isMe()){
            System.out.println("##### listener OrderStatusUpdate : " + packed.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPickedUp_OrderStatusUpdate(@Payload PickedUp pickedUp){

        if(pickedUp.isMe()){
            System.out.println("##### listener OrderStatusUpdate : " + pickedUp.toJson());
        }
    }

}
