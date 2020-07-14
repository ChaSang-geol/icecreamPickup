package icecreamPickup;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long productId;
    private String productName;
    private String customerName;
    private Long storeId;
    private String orderStatus;

    @PostPersist
    public void onPostPersist(){
        IceCreamOrdered iceCreamOrdered = new IceCreamOrdered();
        BeanUtils.copyProperties(this, iceCreamOrdered);
        iceCreamOrdered.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        icecreamPickup.external.PaymentInformation paymentInformation = new icecreamPickup.external.PaymentInformation();
        // mappings goes here
        IcecreamorderApplication.applicationContext.getBean(icecreamPickup.external.PaymentInformationService.class)
            .payment(paymentInformation);


    }

    @PostUpdate
    public void onPostUpdate(){
        IceCreamOrderCanceled iceCreamOrderCanceled = new IceCreamOrderCanceled();
        BeanUtils.copyProperties(this, iceCreamOrderCanceled);
        iceCreamOrderCanceled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }




}
