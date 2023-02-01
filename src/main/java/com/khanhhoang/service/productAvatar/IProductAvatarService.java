package com.khanhhoang.service.productAvatar;

import com.khanhhoang.model.ProductAvatar;
import com.khanhhoang.service.IGeneralService;

public interface IProductAvatarService extends IGeneralService<ProductAvatar> {
    void delete(String id);
}
