package tk.huclele.demo.exception;

import lombok.Getter;
import lombok.Setter;
import tk.huclele.demo.enums.ErrorCodeEnum;

import java.io.Serial;
import java.text.MessageFormat;

/**
 * 自定义业务异常
 */
@Getter
@Setter
public class BizException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7417143966485775110L;
    /**
     * 错误码
     */
    private final Integer code;

    public BizException(ErrorCodeEnum errorCodeEnum, String reason) {
        super(MessageFormat.format(errorCodeEnum.getMessage(), reason));
        this.code = errorCodeEnum.getCode();
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
