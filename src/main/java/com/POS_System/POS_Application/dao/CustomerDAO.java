package com.POS_System.POS_Application.dao;

import com.POS_System.POS_Application.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories

public interface CustomerDAO extends JpaRepository<Customer,Integer > {


    List<Customer> findAllByActiveEquals(boolean activeState);
}
