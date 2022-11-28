package com.khanhhoang.model.dto.product;

import com.khanhhoang.model.Product;
import com.khanhhoang.model.ProductAvatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductUpdateDTO implements Validator {
    private Long id;
    @NotEmpty(message = "Vui lòng nhập tên nhân viên.")
    @Size(min = 5, max = 100, message = "Họ tên có độ dài nằm trong khoảng 5 - 100 ký tự.")
    private String productName;
    private String price;
    private String quantity;
    @NotEmpty(message = "Vui lòng nhập mô tả sản phẩm.")
    private String description;

    private String fileType;

    public Product toProduct(ProductAvatar productAvatar){
        return new Product()
                .setId(id)
                .setProductName(productName)
                .setPrice(new BigDecimal(Long.parseLong(price)))
                .setQuantity(Integer.parseInt(quantity))
                .setDescription(description)
                .setProductAvatar(productAvatar);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductUpdateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductUpdateDTO productUpdateDTO = (ProductUpdateDTO) target;
        String price = productUpdateDTO.getPrice();
        if (price != null && price.length() > 0) {
            if (price.length() > 9){
                errors.rejectValue("price", "price.max","Giá sản phẩm tối đa là 999.999.999 VNĐ");
                return;
            }

            if (price.length() < 6){
                errors.rejectValue("price", "price.min","Giá sản phẩm thấp nhất là 100.000 VNĐ");
                return;
            }

            if (!price.matches("(^$|[0-9]*$)")){
                errors.rejectValue("price",  "price.number","Giá sản phẩm phải là số.");
                return;
            }

        } else {
            errors.rejectValue("price",  "price.null", "Vui lòng nhập giá sản phẩm.");
        }

        String quantity = productUpdateDTO.getQuantity();
        if (quantity != null && quantity.length() > 0) {
            if (quantity.length() > 6){
                errors.rejectValue("quantity", "quantity.max","Số lượng sản phẩm tối đa là 999.999");
                return;
            }

            if (!quantity.matches("(^$|[0-9]*$)")){
                errors.rejectValue("quantity",  "quantity.number","Số lượng sản phẩm phải là số.");
                return;
            }
            Long quantityLong = Long.parseLong(quantity);
            if (quantityLong < 0){
                errors.rejectValue("quantity", "quantity.min","Số lượng sản phẩm không thể âm");
            }
        } else {
            errors.rejectValue("quantity",  "quantity.null", "Vui lòng nhập số lượng sản phẩm.");
        }
    }
}
