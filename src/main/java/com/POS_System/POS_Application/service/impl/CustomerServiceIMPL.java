package com.POS_System.POS_Application.service.impl;

import com.POS_System.POS_Application.dao.CustomerDAO;
import com.POS_System.POS_Application.dto.CustomerDTO;
import com.POS_System.POS_Application.dto.request.CustomerUpdateDTO;
import com.POS_System.POS_Application.entity.Customer;
import com.POS_System.POS_Application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public String saveCustomers(CustomerDTO customerDTO) {
//        System.out.println(customerDTO.getCustomerSalary());
//        return null;


     //   customerDAO.save(customerDTO);

//1st method
        Customer customer=new Customer(
                customerDTO.getCustomerId(),
              customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getCustomerPhones(),
                customerDTO.getNic(),
                customerDTO.isActive()

        );
                    //2nd method  -without constructors
                //        Customer customer1=new Customer();
                //        customer1.setCustomerId(customerDTO.getCustomerId());
                //        customer1.setCustomerName(customerDTO.getCustomerName());
                //        customer1.setCustomerAddress(customerDTO.getCustomerAddress());
                //        customer1.setCustomerSalary(customerDTO.getCustomerSalary());
                //        customer1.setCustomerPhones(customerDTO.getCustomerPhones());
                //        customer1.setNic(customerDTO.getNic());
                //        customer1.setActive(customerDTO.isActive());
                //        customerDAO.save(customer1);
       customerDAO.save(customer);
       //System.out.println("AA");
        return customerDTO.getCustomerName();
    }

    @Override
    public String updateCustomers(CustomerUpdateDTO customerUpdateDTO) {
        if(customerDAO.existsById(customerUpdateDTO.getCustomerId())){
            Customer customer=customerDAO.getReferenceById(customerUpdateDTO.getCustomerId());
            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
            customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary());

            customerDAO.save(customer);
            return customerUpdateDTO.getCustomerName()+" updated successfull";
        }else {
            throw new RuntimeException("Id not found!");
        }
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if (customerDAO.existsById(customerId)){
            Customer customer=customerDAO.getReferenceById(customerId);
            //    return customer;
            CustomerDTO customerDTO=new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getCustomerPhones(),
                    customer.getNic(),
                    customer.isActive()
            );
            return customerDTO;
        }
        else {
            throw new RuntimeException("No customer");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getAllCustomers=customerDAO.findAll();
        //return getAllCustomers;
       if (getAllCustomers.size()>0) {
           List<CustomerDTO> customerDTOList = new ArrayList<>();
           //return customerDTOList;

//        for (int i=0;i<=10;i++){
//
//        }

    //           for(int i=0;i<customerDTOList.size();i++){
    //
    //           }
           for (Customer customer : getAllCustomers) {
               CustomerDTO customerDTO = new CustomerDTO(
                       customer.getCustomerId(),
                       customer.getCustomerName(),
                       customer.getCustomerAddress(),
                       customer.getCustomerSalary(),
                       customer.getCustomerPhones(),
                       customer.getNic(),
                       customer.isActive()
               );
               customerDTOList.add(customerDTO);
           }
           return customerDTOList;
       }else {
           throw new RuntimeException("NOT FOUND");
       }
    }

    @Override
    public String deleteCustomer(int customerId) {
        if (customerDAO.existsById(customerId)){

            customerDAO.deleteById(customerId);

            return customerId+" Deleted Successfully";
        }else {
            throw new RuntimeException("No Customer found in that ID");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean activeState) {
    List<Customer>  getAllCustomersByActiveState =customerDAO.findAllByActiveEquals(activeState);
    List<CustomerDTO>  customerDTOList= new ArrayList<>();

        for (Customer customer:getAllCustomersByActiveState) {
            CustomerDTO customerDTO=new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getCustomerPhones(),
                    customer.getNic(),
                    customer.isActive()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
