package zool.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author：zoolye
 * @date：2018-12-01：23:19
 * @description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

}
