package com.POS_System.POS_Application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

@Table(name="order")
public class Order {

    @Id
    @Column(name="order_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

}
