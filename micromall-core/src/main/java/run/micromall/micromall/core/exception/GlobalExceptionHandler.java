package run.micromall.micromall.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import run.micromall.micromall.service.response.ResponseUtil;
import static run.micromall.micromall.service.response.ResponseUtil.ResponseCode;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 全局捕获异常
 *
 * @author songhaozhi
 * @since : 2021/1/16
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Spirng 非法参数验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseUtil validException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String result = bindingResult.getFieldErrors().get(0).getDefaultMessage();
        log.error("BindException,{}", result);
        return ResponseUtil.fail(result);
    }

    /**
     * javax.validation 非法参数验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseUtil handleConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<ConstraintViolation<?>> list = new ArrayList<>(constraintViolations);
        String result = list.get(0).getMessage();
        log.error("ConstraintViolationException,{}", result);
        return ResponseUtil.fail(result);
    }

    /**
     * 数据库执行异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseUtil handleDataAccessException(DataAccessException e) {
        log.error("Catch an DataAccessException:", e);
        return ResponseUtil.fail(ResponseCode.DATEBASE_EXECUTION_EXCEPTION);
    }

    /**
     * HTTP解析请求参数异常
     *
     * @param exception
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error("httpMessageNotReadableException:", exception);
        return ResponseUtil.fail(ResponseCode.PARAMETER_EXCEPTION, ResponseCode.PARAMETER_PARSE_EXCEPTION);
    }

    /**
     * HTTP
     *
     * @param exception
     */
    @ExceptionHandler(value = HttpMediaTypeException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil httpMediaTypeException(HttpMediaTypeException exception) {
        log.error("httpMediaTypeException:", exception);
        return ResponseUtil.fail(ResponseCode.PARAMETER_EXCEPTION, ResponseCode.HTTP_MEDIA_TYPE_EXCEPTION);
    }

    /**
     * 默认的异常处理
     *
     * @param exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil exceptionHandler(Exception exception) {
        log.error("exception:", exception);
        return ResponseUtil.fail(ResponseCode.SYSTEM_EXCEPTION);
    }

}