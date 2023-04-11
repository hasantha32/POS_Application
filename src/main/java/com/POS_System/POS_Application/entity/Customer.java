package com.POS_System.POS_Application.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.naming.Name;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Setter
//@Getter
//@ToString

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
public class Customer {
    @Id
    @Column(name="customer_id",length=15)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "customer_name",length = 100,nullable = false)
    private String customerName;

    @Column(name = "customer_address",length = 75,nullable = false)
    private String customerAddress;

    @Column(name = "customer_salary")
    private double customerSalary;

    @Type(type = "json")
    @Column(name = "customer_contacts",nullable = false,columnDefinition = "json")
    private ArrayList customerPhones;

    @Column(name = "customer_nic")
    private String nic;

    @Column(name = "active_state",columnDefinition = "TINYINT default 0")
    private boolean active;


}
