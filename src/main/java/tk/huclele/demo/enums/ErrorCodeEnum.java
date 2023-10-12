package tk.huclele.demo.enums;

import lombok.Getter;

/**
 * 自定义错误码枚举类，所有错误码都要在这里定义
 */
@Getter
public enum ErrorCodeEnum {

    /**
     * 参数校验未通过
     */
    ILLEGAL_PARAMETER(24002, "参数校验未通过: {0}"),
    DATA_EXCEPTION(24003, "数据异常: {0}"),
    THE_FILE_HAS_NOT_BEEN_GENERATED_YET(24004, "文件暂未生成: {0}"),

    /**
     * feign第三方接口出错
     */
    FEIGN_API_REQUEST_FAILED(40000, "调用第三方接口报错: {0}"),

    /**
     * 获取登录用户异常
     */
    UN_LOGIN_USER(40100, "获取登录用户异常: {0}");


    private final Integer code;
    private final String message;

    ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
