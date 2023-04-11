package com.POS_System.POS_Application.service;

import com.POS_System.POS_Application.dto.CustomerDTO;
import com.POS_System.POS_Application.dto.request.CustomerUpdateDTO;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public interface CustomerService {
    public String  saveCustomers(CustomerDTO customerDTO);

    String updateCustomers(CustomerUpdateDTO customerUpdateDTO);

    CustomerDTO getCustomerById(int customerId);

    List<CustomerDTO> getAllCustomers();

    String deleteCustomer(int customerId);

    List<CustomerDTO> getAllCustomersByActiveState(boolean activeState);
}
