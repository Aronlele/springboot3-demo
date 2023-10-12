package tk.huclele.demo.exception;

import tk.huclele.demo.enums.ErrorCodeEnum;

import java.io.Serial;

/**
 * @author : xuelele
 * @since : 2023/09/26 15:53
 */
public class UnLoginException extends BizException {

    @Serial
    private static final long serialVersionUID = -7959975084108244990L;

    public UnLoginException(ErrorCodeEnum errorCodeEnum, String reason) {
        super(errorCodeEnum, reason);
    }

    public UnLoginException(Integer code, String message) {
        super(code, message);
    }

    /**
     * 错误码
     */
    @Override
    public Integer getCode() {
        return super.getCode();
    }
}
