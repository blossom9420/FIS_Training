package vn.fis.training.service;

import vn.fis.training.domain.Customer;
import vn.fis.training.exception.CustomerNotFoundException;
import vn.fis.training.exception.DuplicateCustomerException;
import vn.fis.training.exception.InvalidCustomerException;
import vn.fis.training.store.InMemoryCustomerStore;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleCustomerService implements CustomerService {

    private InMemoryCustomerStore customerStore;

    @Override
    public Customer findById(String id) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        if (isNullOrEmpty(id)) {
            throw new IllegalArgumentException("Không thể tìm kiếm với empty ID");
        }
        // cách nông dân
        //List<Customer> listCustomer= customerStore.findAll();
        //for (int i = 0; i < listCustomer.size(); i++) {
        //   if(listCustomer.get(i).getId().equals(id)){
        //        return  listCustomer.get(i);
        //    }
        //}
        //throw new CustomerNotFoundException(
        //        String.format("Không tìm thấy customer với id: %s",id));


        // cách dùng money
        return customerStore.findAll().stream().filter(customer -> {
            return id.equals(customer.getId());
        }).findFirst().orElseThrow(() -> {
            throw new CustomerNotFoundException(
                    String.format("Không tìm thấy customer với id: %s", id));
        });
    }

    private boolean isNullOrEmpty(String id) {
        if (id == null || id.trim().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Customer createCustomer(Customer customer) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        validate(customer);
        checkDuplicate(customer);
        // chuẩn hóa :standardize
        String standardize = customer.standardize(customer.getName());
        customer.setName(standardize);
        return customerStore.insertOrUpdate(customer);
    }

    private void checkDuplicate(Customer customer) {
        try{
            if (customerStore.findAll().stream().anyMatch(cust ->{
                return cust.getMobile().equals(customer.getMobile());
            })){
                throw  new DuplicateCustomerException(customer, String.format(" Customer with phone number %s is duplicated. ",customer.getMobile()));
            }
        }catch (CustomerNotFoundException customerNotFoundException){
            // NOOP
        }
    }

    private void validate(Customer customer) {
        if(isNullOrEmpty(customer.getName())){
            throw new InvalidCustomerException(customer,"Customer name is empty");
        }
        if(isNullOrEmpty(customer.getMobile())){
            throw new InvalidCustomerException(customer,"Customer mobile is empty");
        }
        int checkName = customer.checkName(customer.getName());
        switch (checkName){
            case 0: throw new InvalidCustomerException(customer,"Customer name is empty");
            case -1: throw new InvalidCustomerException(customer," Length of name must be between 5 and 50");
            case -2: throw new InvalidCustomerException(customer," Name is only alphabet");
            default:  // = 1 : pass
                break;
        }

        int checkBirthday = customer.checkBirthDay(customer.getBirthDay());
        switch (checkBirthday){
            case 0: throw new InvalidCustomerException(customer,"Customer dob is empty");
            case -1: throw new InvalidCustomerException(customer," Age must be >= 10");
            default:  // = 1 : pass
                break;
        }

        int checkMobile = customer.checkMobile(customer.getMobile());
        switch (checkMobile){
            case 0: throw new InvalidCustomerException(customer,"Phone number must be start with 0 ");
            case -1: throw new InvalidCustomerException(customer," Length of phone number must be 10");
            case -2: throw new InvalidCustomerException(customer," Phone number must be only number");
            default:  // = 1 : pass
                break;
        }

        int checkStatus = customer.CheckStatus(customer.getStatus());
        if(checkStatus == 0){
            throw new InvalidCustomerException(customer,"Status is null ");
        }

    }

    @Override
    public Customer updateCustomer(Customer customer) {
        // validate(customer)
        // checkExist(customer)
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        return customerStore.insertOrUpdate(customer);
    }

    @Override
    public void deleteCustomerById(String id) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        if (isNullOrEmpty(id)) {
            throw new IllegalArgumentException(" Khong the xoa voi empty id");
        }
        findById(id);
        customerStore, deleteCustomerById(id);
    }

    @Override
    public List<Customer> findAllOrderByNameAsc() {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        return customerStore.findAll().stream()
                .sorted(Comparator.comparing(Customer::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllOrderByNameLimit(int limit) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        return customerStore.findAll().stream()
                .sorted(Comparator.comparing(Customer::getName))
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllCustomerByNameLikeOrderByNameAsc(String custName, String limit) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        return null;
    }

    @Override
    public List<SummaryCustomerByAgeDTO> summaryCustomerByAgeOrderByAgeDesc() {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        return null;
    }

}
