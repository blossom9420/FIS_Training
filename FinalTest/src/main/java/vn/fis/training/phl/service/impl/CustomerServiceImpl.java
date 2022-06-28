package vn.fis.training.phl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.fis.training.phl.dto.customer.CustomerDTO;
import vn.fis.training.phl.dto.customer.NewCustomerDTO;
import vn.fis.training.phl.exception.CustomerNotFoundException;
import vn.fis.training.phl.exception.MobileExistedException;
import vn.fis.training.phl.model.Customer;
import vn.fis.training.phl.repository.CustomerRepository;
import vn.fis.training.phl.service.CustomerService;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<CustomerDTO> findAll(Pageable pageable) {
        log.info("Query all customer: PageNumber: {}, PageSize: {}", pageable.getPageNumber(), pageable.getPageSize());
        return customerRepository.findAll(pageable).map(CustomerDTO.Mapper::mapFromCustomerEntity);
    }

    @Override
    public Customer findById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> {
            try {
                throw new CustomerNotFoundException(String.format("Not found customer with id %s", customerId));
            } catch (CustomerNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Service: Customer: {}", customer);
        return customer;
    }

    @Override
    public Page<CustomerDTO> create(NewCustomerDTO newCustomerDTO, Pageable pageable) {
        Customer customer = Customer.builder()
                .name(newCustomerDTO.getName())
                .mobile(newCustomerDTO.getMobile())
                .address(newCustomerDTO.getAddress())
                .build();
        customerRepository.save(customer);
        log.info("Saved Customer: {}", customer);
        return findAll(pageable);
    }

    @Override
    public CustomerDTO update(Long customerId, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByMobile(customerDTO.getMobile());
        if(null != customer) {
            try {
                throw new MobileExistedException(String.format("Mobile %s is existed!!!", customerDTO.getMobile()));
            } catch (MobileExistedException e) {
                throw new RuntimeException(e);
            }

        }
        Customer savedCustomer = customerRepository.findById(customerId).get();
        savedCustomer.setAddress(customerDTO.getAddress());
        savedCustomer.setMobile(customerDTO.getMobile());
        Customer updatedCustomer = customerRepository.save(savedCustomer);
        log.info("Customer update after: {}", updatedCustomer);
        return CustomerDTO.Mapper.mapFromCustomerEntity(updatedCustomer);
    }

    @Override
    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
