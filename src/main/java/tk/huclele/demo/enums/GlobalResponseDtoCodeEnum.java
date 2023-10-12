package tk.huclele.demo.enums;

import lombok.Getter;

/**
 * 全局响应dto对象编码.
 *
 * @author xuelele
 * @version 1.0
 * @since 2023/9/28 17:23
 */
@Getter
public enum GlobalResponseDtoCodeEnum {

    SUCCESS(10000, "成功"),
    NOT_LOGIN(24000, "未登录认证"),
    UNAUTHORIZED(24001, "无权限访问"),
    SERVER_ERROR(50000, "系统服务异常");

    private final Integer code;
    private final String msg;

    GlobalResponseDtoCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
