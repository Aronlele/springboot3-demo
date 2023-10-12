package tk.huclele.demo.handler;

import com.google.common.base.Throwables;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.huclele.demo.dto.GlobalResponseDto;
import tk.huclele.demo.enums.ErrorCodeEnum;
import tk.huclele.demo.enums.GlobalResponseDtoCodeEnum;
import tk.huclele.demo.exception.BizException;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类.
 *
 * @author xuelele
 * @version 1.0
 * @since 2023/10/7 10:05
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public GlobalResponseDto<String> defaultExceptionHandler(Exception ex) {

        this.exceptionInfoHandle(ex);

        // 参数校验异常 -- post请求的对象参数校验失败后抛出的异常
        if (ex instanceof MethodArgumentNotValidException) {
            return this.failResponse(((MethodArgumentNotValidException) ex).getBindingResult());
        }


        // 参数校验异常 -- get请求的对象参数校验失败后抛出的异常
        if (ex instanceof BindException) {
            return this.failResponse(((BindException) ex).getBindingResult());
        }

        // 参数校验异常 -- 单参数校验失败后抛出的异常
        if (ex instanceof ConstraintViolationException) {
            String errorMsg = ex.getMessage();
            return GlobalResponseDto.fail(ErrorCodeEnum.ILLEGAL_PARAMETER.getCode(),
                    MessageFormat.format(ErrorCodeEnum.ILLEGAL_PARAMETER.getMessage(), errorMsg));
        }

        // 参数校验异常 -- 缺少必需的参数抛出的异常
        if (ex instanceof MissingServletRequestParameterException) {
            String errorMsg = ((MissingServletRequestParameterException) ex).getMessage();
            return GlobalResponseDto.fail(ErrorCodeEnum.ILLEGAL_PARAMETER.getCode(),
                    MessageFormat.format(ErrorCodeEnum.ILLEGAL_PARAMETER.getMessage(), errorMsg));
        }
        if (ex instanceof IllegalArgumentException iex) {
            return GlobalResponseDto.fail(ErrorCodeEnum.ILLEGAL_PARAMETER.getCode(),
                    MessageFormat.format(ErrorCodeEnum.ILLEGAL_PARAMETER.getMessage(), iex.getMessage()));
        }

        // 自定义业务异常
        if (ex instanceof BizException bz) {
            return GlobalResponseDto.fail(bz.getCode(), bz.getMessage());
        }


        // 系统内部其它异常
        return GlobalResponseDto.fail(GlobalResponseDtoCodeEnum.SERVER_ERROR.getCode(), MessageFormat.format(GlobalResponseDtoCodeEnum.SERVER_ERROR.getMsg(), ex.toString()));
    }

    /**
     * 参数校验失败的返回处理
     *
     * @param bindingResult 结果
     * @return 响应
     */
    private GlobalResponseDto<String> failResponse(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder("[");
        bindingResult.getFieldErrors().forEach(fieldError ->
                errorMsg.append(fieldError.getField())
                        .append(":")
                        .append(fieldError.getDefaultMessage())
                        .append("; "));
        errorMsg.append("]");
        return GlobalResponseDto.fail(ErrorCodeEnum.ILLEGAL_PARAMETER.getCode(), MessageFormat.format(ErrorCodeEnum.ILLEGAL_PARAMETER.getMessage(), errorMsg));
    }

    /**
     * 异常信息处理方法
     *
     * @param ex 异常
     */
    private void exceptionInfoHandle(Exception ex) {
        ServletRequestAttributes servletContainer = null;
        try {
            servletContainer = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        } catch (Exception e) {
            log.error("exceptionInfoHandle fail:{}", Throwables.getStackTraceAsString(ex));
        }
        if (servletContainer == null) {
            log.error("Exception Stack Trace:{}", Throwables.getStackTraceAsString(ex));
            return;
        }

        HttpServletRequest request = servletContainer.getRequest();
        String uri;
        uri = request.getRequestURI();
        if (request.getQueryString() != null) {
            uri = uri + "?" + request.getQueryString();
        }
        log.error("Uri:{}, Exception Stack Trace:{}", uri, Throwables.getStackTraceAsString(ex));

        // 异常记录
        Map<String, Object> params = new HashMap<>(6);
        params.put("method", request.getMethod());
        params.put("uri", uri);
        params.put("exception", ex.getClass().getName());
        params.put("message", ex.getMessage());
        params.put("body", this.getRequestBody(request));
        log.error("异常记录:{}", params);
    }

    /**
     * 获取请求体内容
     *
     * @param request 请求
     * @return 请求体
     */
    private String getRequestBody(HttpServletRequest request) {
        byte[] bodyBytes;
        String body = "";
        try {
            bodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
            body = new String(bodyBytes, request.getCharacterEncoding());
        } catch (IOException e) {
            log.info("exceptionInfoHandle-getRequestBody exception:{}", e.toString());
        }
        return body;
    }
}
