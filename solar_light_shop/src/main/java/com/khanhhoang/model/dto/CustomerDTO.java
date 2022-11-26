package com.khanhhoang.model.dto;

import com.khanhhoang.model.Customer;
import com.khanhhoang.model.LocationRegion;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private Long id;

    @NotEmpty(message = "Vui lòng nhập tên khách hàng")
    @Size(min = 5, max = 100, message = "Họ tên có độ dài nằm trong khoảng 5 - 100 ký tự")
    private String fullName;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email không đúng định dạng")
    @NotEmpty(message = "Vui lòng nhập email")
    private String email;

    @NotEmpty(message = "Vui lòng nhập số điện thoại")
    private String phone;


    @Valid
    private LocationRegionDTO locationRegion;

    public CustomerDTO(Long id, String fullName, String email, String phone, LocationRegion locationRegion) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }

    public Customer toCustomer(){
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setLocationRegion(locationRegion.toLocationRegion());
    }
}
