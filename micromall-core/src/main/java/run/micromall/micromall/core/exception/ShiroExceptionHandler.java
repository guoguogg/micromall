package run.micromall.micromall.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import run.micromall.micromall.service.utils.ResponseUtil;

/**
 * @author Administrator
 */
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ShiroExceptionHandler {


    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Object unauthenticatedHandler(AuthenticationException e) {
        log.warn(e.getMessage(), e);
        return ResponseUtil.notLogin();
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public Object unauthorizedHandler(AuthorizationException e) {
        log.warn(e.getMessage(), e);
        return ResponseUtil.notAuthorized();
    }

}