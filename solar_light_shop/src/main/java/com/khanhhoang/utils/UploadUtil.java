package com.khanhhoang.utils;

import com.cloudinary.utils.ObjectUtils;
import com.khanhhoang.exception.DataInputException;
import com.khanhhoang.model.Customer;
import com.khanhhoang.model.CustomerAvatar;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UploadUtil {
    public static final String IMAGE_UPLOAD_FOLDER = "avatar_images";

    public Map buildImageUploadParams(CustomerAvatar customerAvatar) {
        if (customerAvatar == null || customerAvatar.getId() == null)
            throw new DataInputException("Không thể upload hình ảnh của sản phẩm chưa được lưu");

        String publicId = String.format("%s/%s", IMAGE_UPLOAD_FOLDER, customerAvatar.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageDestroyParams(Customer customer, String publicId) {
        if (customer == null)
            throw new DataInputException("Không thể destroy hình ảnh của sản phẩm không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }
}
