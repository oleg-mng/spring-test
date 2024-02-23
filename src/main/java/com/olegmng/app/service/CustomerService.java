package com.olegmng.app.service;

import com.olegmng.app.api.CustomerResponse;
import com.olegmng.app.model.Customer;
import com.olegmng.app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public List<CustomerResponse> getAll(){
        return customerRepository.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    };

    public Optional<CustomerResponse> findById(Long id){
        return customerRepository.findById(id)
                .map(this::map);
    };

    public CustomerResponse map(Customer customer){
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setName(customer.getName());
        return  response;
    }
}
