package tk.huclele.demo.enums;

import lombok.Getter;

/**
 * 资产目录查询参数.
 *
 * @author xuelele
 * @version 1.0
 * @since 2023/10/11 10:09
 */
@Getter
public enum AssetCatalogQueryParamEnum {

    DOMAIN("domain"),

    PRODUCT("product"),

    SUB_PRODUCT("sub_product"),

    MODULE("module");

    private final String value;


    AssetCatalogQueryParamEnum(String value) {
        this.value = value;
    }


}
