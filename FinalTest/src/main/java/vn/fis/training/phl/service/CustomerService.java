package vn.fis.training.phl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.fis.training.phl.dto.customer.CustomerDTO;
import vn.fis.training.phl.dto.customer.NewCustomerDTO;
import vn.fis.training.phl.model.Customer;

public interface CustomerService {
    Page<CustomerDTO> findAll(Pageable pageable);
    Customer findById(Long customerId);

    Page<CustomerDTO> create(NewCustomerDTO newCustomerDTO, Pageable pageable);

    CustomerDTO update(Long customerId, CustomerDTO customerDTO);

    void delete(Long customerId);
}
