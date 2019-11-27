package com.lynas.reactivespringjava;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Order {

    @Id
    public String id;

    public String customerId;
    public String orderItemName;

    public Order(String customerId, String orderItemName) {
        this.customerId = customerId;
        this.orderItemName = orderItemName;
    }
}

