package com.khanhhoang.service.customer;

import com.khanhhoang.model.Customer;
import com.khanhhoang.model.CustomerAvatar;
import com.khanhhoang.model.LocationRegion;
import com.khanhhoang.model.dto.CustomerAvatarCreateDTO;
import com.khanhhoang.model.dto.CustomerAvatarDTO;
import com.khanhhoang.model.dto.CustomerDTO;
import com.khanhhoang.model.dto.CustomerUpdateDTO;
import com.khanhhoang.service.IGeneralService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    List<CustomerDTO> getAllCustomerDTO();

//    Customer save(Customer customer, LocationRegion locationRegion);

    List<CustomerAvatarDTO> getAllCustomerAvatarDTO();

    void softDelete(long customerId);

    List<Customer> findAllByIdNot(long id);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);

    Optional<CustomerDTO> getByEmailDTO(String email);

    CustomerAvatar createWithAvatar(CustomerAvatarCreateDTO customerAvatarCreateDTO, LocationRegion locationRegion);


    CustomerAvatar saveWithAvatar(CustomerUpdateDTO customerUpdateDTO, MultipartFile file, LocationRegion locationRegion);
}
