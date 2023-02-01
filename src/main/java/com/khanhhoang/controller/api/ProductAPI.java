package com.khanhhoang.controller.api;

import com.khanhhoang.exception.DataInputException;
import com.khanhhoang.exception.EmailExistsException;
import com.khanhhoang.model.Product;
import com.khanhhoang.model.dto.product.ProductCreateDTO;
import com.khanhhoang.model.dto.product.ProductDTO;
import com.khanhhoang.model.dto.product.ProductUpdateDTO;
import com.khanhhoang.service.product.IProductService;
import com.khanhhoang.service.productAvatar.IProductAvatarService;
import com.khanhhoang.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {
    @Autowired
    private IProductService productService;

    @Autowired
    private IProductAvatarService productAvatarService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getAllByDeletedIsFalse() {
        List<ProductDTO> productDTOS = productService.getAllProductDTO();
        if (productDTOS.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getById(@PathVariable String productId) {
        long pid;
        try {
            pid = Long.parseLong(productId);
        } catch (NumberFormatException e) {
            throw new DataInputException("ID sản phẩm không hợp lệ.");
        }

        Optional<Product> productOptional = productService.findById(pid);

        if (!productOptional.isPresent()) {
            throw new DataInputException("ID sản phẩm không hợp lệ.");
        }

        return new ResponseEntity<>(productOptional.get().toProductDTO(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated ProductCreateDTO productCreateDTO, BindingResult bindingResult) {
        new ProductCreateDTO().validate(productCreateDTO, bindingResult);
        MultipartFile imageFile = productCreateDTO.getFile();

        if (imageFile == null) {
            throw new DataInputException("Vui lòng chọn hình ảnh.");
        }

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        if (productService.existsByProductName(productCreateDTO.getProductName())) {
            throw new EmailExistsException("Sản phẩm đã tồn tại trong hệ thống.");
        }

        productCreateDTO.setId(null);
        Product newProduct = productService.createWithAvatar(productCreateDTO);

        return new ResponseEntity<>(newProduct.toProductDTO(), HttpStatus.CREATED);
    }



    @PatchMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ADMINISTRATORS')")
    public ResponseEntity<?> update(@PathVariable Long productId, MultipartFile file, @Validated ProductUpdateDTO productUpdateDTO, BindingResult bindingResult) {
        Optional<Product> productOptional = productService.findById(productId);
        if (!productOptional.isPresent()) {
            throw new DataInputException("ID sản phẩm không tồn tại.");
        }

        Product product = productOptional.get();

        if (productService.existsByProductNameAndIdNot(productUpdateDTO.getProductName(), productId)) {
            throw new DataInputException("Sản phẩm đã tồn tại trong hệ thống.");
        }

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        String productName = productUpdateDTO.getProductName();
        BigDecimal price = new BigDecimal(Long.parseLong(productUpdateDTO.getPrice()));
        int quantity = Integer.parseInt(productUpdateDTO.getQuantity());
        String description = productUpdateDTO.getDescription();

        product.setProductName(productName)
                .setPrice(price)
                .setQuantity(quantity)
                .setDescription(description);
        product = productService.save(product);

        if(file != null){
            product = productService.saveWithAvatar(product, file);
        }
        return new ResponseEntity<>(product.toProductDTO(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ADMINISTRATORS')")
    public ResponseEntity<?> delete(@PathVariable Long productId) {

        Optional<Product> productOptional = productService.findById(productId);

        if (!productOptional.isPresent()) {
            throw new DataInputException("ID khách hàng không hợp lệ");
        }

        Product product = productOptional.get();
        product.setDeleted(true);
        productService.save(product);

        return new ResponseEntity<>(productOptional.get().toProductDTO(), HttpStatus.OK);
    }
}
