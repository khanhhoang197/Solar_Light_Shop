package com.khanhhoang.repository;

import com.khanhhoang.model.Customer;
import com.khanhhoang.model.dto.CustomerAvatarDTO;
import com.khanhhoang.model.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT NEW com.khanhhoang.model.dto.CustomerDTO (" +
            "c.id, " +
            "c.fullName, " +
            "c.email, " +
            "c.phone, " +
            "c.locationRegion" +
            ") FROM Customer AS c " +
            "WHERE c.deleted = false "
    )
    List<CustomerDTO> getAllCustomerDTO();

    @Query("SELECT NEW com.khanhhoang.model.dto.CustomerDTO (" +
            "c.id, " +
            "c.fullName, " +
            "c.email, " +
            "c.phone, " +
            "c.locationRegion" +
            ") FROM Customer AS c " +
            "WHERE c.deleted = false " +
            "AND c.email = :email"
//         +   "AND c = 4"
    )
    Optional<CustomerDTO> getByEmailDTO(@Param("email") String email);

    @Query("SELECT NEW com.khanhhoang.model.dto.CustomerDTO (" +
            "c.id, " +
            "c.fullName, " +
            "c.email, " +
            "c.phone, " +
            "c.locationRegion" +
            ") FROM Customer AS c " +
            "WHERE c.deleted = true "
    )
    List<CustomerAvatarDTO> getAllCustomerByDeletedIsTrue();

    @Modifying
    @Query("UPDATE Customer AS c SET c.deleted = true WHERE c.id = :customerId")
    void softDelete(@Param("customerId") long customerId);

    @Modifying
    @Query("UPDATE Customer AS c SET c.deleted = false WHERE c.id = :customerId")
    void restore(@Param("customerId") long customerId);

    List<Customer> findAllByIdNot(long id);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);

}

