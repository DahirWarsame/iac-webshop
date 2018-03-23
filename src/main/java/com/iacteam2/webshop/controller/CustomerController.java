package com.iacteam2.webshop.controller;


import com.iacteam2.webshop.exception.ResourceNotFoundException;
import com.iacteam2.webshop.model.Account;
import com.iacteam2.webshop.model.Address;
import com.iacteam2.webshop.model.Customer;
import com.iacteam2.webshop.repository.AccountRepository;
import com.iacteam2.webshop.repository.AddressRepository;
import com.iacteam2.webshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rest")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AccountRepository accountRepository;

    // Get All Notes
    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    @GetMapping("/account")
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    @GetMapping("/address")
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable(value = "id") Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
    }

    @PostMapping("/customer/new")
    public Customer createCustomer(@Valid @
            RequestBody Customer customer) {

        return customerRepository.save(customer);
    }

    @PostMapping("/address/new")
    public Address createAddress(@Valid @
            RequestBody Address address) {

        return addressRepository.save(address);
    }
    @PostMapping("/account/new")
    public Account createAccount(@Valid @RequestBody Account account){
        return accountRepository.save(account);
    }

    @PutMapping("/customer/{id}")
    public Customer updateCustomer(@PathVariable(value = "id") Long customerId,
                                   @Valid @RequestBody Customer customerDetails) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setAddress(customerDetails.getAddress());

        Customer updatedCustomer = customerRepository.save(customer);
        return updatedCustomer;
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

        customerRepository.delete(customer);

        return ResponseEntity.ok().build();
    }




}
