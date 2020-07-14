package icecreamPickup;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Sales_table")
public class Sales {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long storeId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        StoreOrderReceived storeOrderReceived = new StoreOrderReceived();
        BeanUtils.copyProperties(this, storeOrderReceived);
        storeOrderReceived.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        PickedUp pickedUp = new PickedUp();
        BeanUtils.copyProperties(this, pickedUp);
        pickedUp.publishAfterCommit();


        Packed packed = new Packed();
        BeanUtils.copyProperties(this, packed);
        packed.publishAfterCommit();


        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
