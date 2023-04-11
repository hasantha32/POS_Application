package com.POS_System.POS_Application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDTO {
    private int customerId;

    private String customerName;
    private String customerAddress;
    private double customerSalary;
    private ArrayList customerPhones;
    private String nic;
    private boolean active;

}

