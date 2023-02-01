package com.khanhhoang.service.customer;

import com.khanhhoang.exception.DataInputException;
import com.khanhhoang.model.Customer;
import com.khanhhoang.model.CustomerAvatar;
import com.khanhhoang.model.LocationRegion;
import com.khanhhoang.model.dto.CustomerAvatarCreateDTO;
import com.khanhhoang.model.dto.CustomerAvatarDTO;
import com.khanhhoang.model.dto.CustomerDTO;
import com.khanhhoang.model.dto.CustomerUpdateDTO;
import com.khanhhoang.model.enums.FileType;
import com.khanhhoang.repository.CustomerRepository;
import com.khanhhoang.repository.LocationRegionRepository;
import com.khanhhoang.service.customerAvatar.ICustomerAvatarService;
import com.khanhhoang.upload.IUploadService;
import com.khanhhoang.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Autowired
    private ICustomerAvatarService customerAvatarService;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private UploadUtil uploadUtil;

    @Override
    public List<CustomerDTO> getAllCustomerDTO() {
        return customerRepository.getAllCustomerDTO();
    }

    @Override
    public List<CustomerDTO> getAllCustomerByDeletedIsTrue(){
        return customerRepository.getAllCustomerByDeletedIsTrue();
    }


    @Override
    public List<CustomerAvatarDTO> getAllCustomerAvatarDTO() {
        return customerAvatarService.getAllCustomerAvatarDTO();
    }

    @Override
    public List<CustomerAvatarDTO> getAllDeletedCustomerAvatarDTO() {
        return customerAvatarService.getAllDeletedCustomerAvatarDTO();
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        locationRegionRepository.save(customer.getLocationRegion());
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void softDelete(long customerId) {
        customerRepository.softDelete(customerId);
    }

    @Override
    public void restore(long customerId) {
        customerRepository.restore(customerId);
    }

    @Override
    public List<Customer> findAllByIdNot(long id) {
        return customerRepository.findAllByIdNot(id);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findByEmailAndIdIsNot(String email, Long id) {
        return customerRepository.findByEmailAndIdIsNot(email, id);
    }

    @Override
    public Optional<CustomerDTO> getByEmailDTO(String email) {
        return customerRepository.getByEmailDTO(email);
    }


    @Override
    public CustomerAvatar createWithAvatar(CustomerAvatarCreateDTO customerAvatarCreateDTO, LocationRegion locationRegion) {

        locationRegion = locationRegionRepository.save(locationRegion);
        Customer customer = customerAvatarCreateDTO.toCustomer(locationRegion);
        customer = customerRepository.save(customer);

        String fileType = customerAvatarCreateDTO.getFile().getContentType();
        assert fileType != null;
        fileType = fileType.substring(0, 5);

        CustomerAvatar customerAvatar = new CustomerAvatar();
        customerAvatar.setCustomer(customer).setFileType(fileType);
        customerAvatar = customerAvatarService.save(customerAvatar);

        CustomerAvatar newCustomerAvatar = new CustomerAvatar();
        if (fileType.equals(FileType.IMAGE.getValue())) {
            newCustomerAvatar = uploadAndSaveCustomerImage(customerAvatarCreateDTO.getFile(), customerAvatar, customer);
        }
        return newCustomerAvatar;
    }

    private CustomerAvatar uploadAndSaveCustomerImage(MultipartFile file, CustomerAvatar customerAvatar, Customer customer) {
        try {
            Map uploadResult = uploadService.uploadImage(file, uploadUtil.buildImageUploadParams(customerAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            customerAvatar.setFileName(customerAvatar.getId() + "." + fileFormat);
            customerAvatar.setFileUrl(fileUrl);
            customerAvatar.setFileFolder(UploadUtil.IMAGE_UPLOAD_FOLDER);
            customerAvatar.setCloudId(customerAvatar.getFileFolder() + "/" + customerAvatar.getId());
            customerAvatar.setCustomer(customer);
            return customerAvatarService.save(customerAvatar);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }
    }

    @Override
    public CustomerAvatar saveWithAvatar(CustomerUpdateDTO customerUpdateDTO, MultipartFile file, LocationRegion locationRegion) {

        locationRegion = locationRegionRepository.save(locationRegion);
        Customer customer = customerUpdateDTO.toCustomer(locationRegion);
        customer = customerRepository.save(customer);

        CustomerAvatar oldCustomerAvatar = customerAvatarService.getCustomerAvatarById(customer.getId()).toCustomerAvatar();
        customerAvatarService.delete(oldCustomerAvatar.getId());

        String fileType = file.getContentType();
        assert fileType != null;
        fileType = fileType.substring(0, 5);

        CustomerAvatar customerAvatar = new CustomerAvatar();
        customerAvatar.setCustomer(customer).setFileType(fileType);
        customerAvatar = customerAvatarService.save(customerAvatar);

        CustomerAvatar newCustomerAvatar = new CustomerAvatar();
        if (fileType.equals(FileType.IMAGE.getValue())) {
            newCustomerAvatar = uploadAndSaveCustomerImage(file, customerAvatar, customer);
        }
        return newCustomerAvatar;
    }
}
