package tk.huclele.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tk.huclele.demo.enums.GlobalResponseDtoCodeEnum;

import java.io.Serial;
import java.io.Serializable;

/**
 * 全局响应dto对象.
 *
 * @author xuelele
 * @version 1.0
 * @since 2023/9/28 17:16
 */

@Data
public class GlobalResponseDto<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 4411866362791108501L;

    @Schema(description = "响应码")
    private Integer code;

    @Schema(description = "响应消息")
    private String msg;

    private T data;

    public GlobalResponseDto() {
    }

    public static <T> GlobalResponseDto<T> success(T data) {
        return restResult(data, GlobalResponseDtoCodeEnum.SUCCESS.getCode(), GlobalResponseDtoCodeEnum.SUCCESS.getMsg());
    }

    public static <T> GlobalResponseDto<T> fail(Integer errorCode, String errorMsg) {
        return restResult(null, errorCode, errorMsg);
    }

    public static <T> GlobalResponseDto<T> fail(Integer errorCode, String errorMsg, T data) {
        return restResult(data, errorCode, errorMsg);
    }

    private static <T> GlobalResponseDto<T> restResult(T data, Integer code, String msg) {
        GlobalResponseDto<T> apiResult = new GlobalResponseDto<>();
        apiResult.setData(data);
        apiResult.setCode(code);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public boolean ifFail() {
        return !this.ifSuccess();
    }

    public boolean ifSuccess() {
        return GlobalResponseDtoCodeEnum.SUCCESS.getCode().equals(this.code);
    }
}
