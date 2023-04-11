package com.POS_System.POS_Application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerUpdateDTO {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;

}
