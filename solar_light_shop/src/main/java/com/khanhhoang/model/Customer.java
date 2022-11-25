//package com.khanhhoang.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.List;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "customers")
//public class Customer extends BaseEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "full_name", nullable = false)
//    private String fullName;
//
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    private String phone;
//
//    @OneToOne
//    private LocationRegion locationRegion;
//
//    @Column(precision = 12, scale = 0, nullable = false, updatable = false)
//    private BigDecimal balance;
//
//
//    public CustomerDTO toCustomerDTO() {
//        return new CustomerDTO()
//                .setId(id)
//                .setFullName(fullName)
//                .setEmail(email)
//                .setPhone(phone)
//                .setBalance(balance.toString())
//                .setLocationRegion(locationRegion.toLocationRegionDTO());
//
//    }
//}