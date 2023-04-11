package com.POS_System.POS_Application.controller;

import com.POS_System.POS_Application.dto.CustomerDTO;
import com.POS_System.POS_Application.dto.request.CustomerUpdateDTO;
import com.POS_System.POS_Application.service.CustomerService;
import com.POS_System.POS_Application.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customercontroller")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public String saveCustomers(@RequestBody CustomerDTO customerDTO){
//        CustomerServiceIMPL customerServiceIMPLl=new CustomerServiceIMPL();
//        customerServiceIMPLl.saveCustomer(customerDTO);

        customerService.saveCustomers(customerDTO);

//        String name=customerDTO.getCustomerName();
//        System.out.println(name);
        return "Saved";
    }
    @PutMapping("/update")
    public String updateCustomers(@RequestBody CustomerUpdateDTO customerUpdateDTO){
 //        customerService.updateCustomer(customerUpdateDTO);
//        return "Updated";

        String message = customerService.updateCustomers(customerUpdateDTO);
        return message;
    }

    @GetMapping( path="/get-by-id",params = "id")
    public CustomerDTO getCustomerById(@RequestParam(value="id") int customerId){
//        System.out.println("print id"+customerId);
//        return null;

    CustomerDTO customerDTO=customerService.getCustomerById(customerId);
    return customerDTO;
    }

    //######################################################################################//
////####################$$$$$$$$$$$    SaveItem with normal method (String return type)   $$$$$#######################
//                    @GetMapping("get-all-customers")
//                    public List<CustomerDTO> getAllCustomers(){
//                        List<CustomerDTO> allCustomers=customerService.getAllCustomers();
//                        return allCustomers;
//                    }

    //######################################################################################//
////##############$$$$$$$$$$    SaveItem with Response Entity method (ResponseEntity return type)   $$$$$##################//
    @GetMapping("get-all-customers")
    public ResponseEntity<StandardResponse> getAllCustomers(){
        List<CustomerDTO> allCustomers=customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",allCustomers),
                HttpStatus.OK
        );
    }


    @DeleteMapping(path = "/delete-customer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") int customerId){
        String deleted=customerService.deleteCustomer(customerId);
        return deleted;
    }






    @GetMapping("/get-all-customers-by-active-state{status}")
    public List<CustomerDTO> getAllCustemorsByActiveState(@PathVariable(value = "status") boolean activeState){
        List<CustomerDTO> allCustomers=customerService.getAllCustomersByActiveState(activeState);
        return allCustomers;
    }
}
