package run.micromall.micromall.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.micromall.micromall.service.response.ResponseCode;
import run.micromall.micromall.service.response.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局Error/404处理
 *
 * @author songhaozhi
 * @since : 2021/1/16
 */
@RestController
@Slf4j
public class GlobalErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public ResponseUtil handleError(HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();
        log.info("response status = " + status);
        switch (status) {
            case HttpServletResponse.SC_UNAUTHORIZED:
                log.error("Unauthorized");
                return ResponseUtil.fail(ResponseCode.UNAUTHORIZED);
            case HttpServletResponse.SC_FORBIDDEN:
                log.error("Permission denied");
                return ResponseUtil.fail(ResponseCode.NOT_PERMISSION);
            case HttpServletResponse.SC_NOT_FOUND:
                log.error("404 NOT FOUND");
                return ResponseUtil.fail(ResponseCode.NOT_FOUND);
            default:
                log.error("ERROR...");
                break;
        }
        return ResponseUtil.fail(ResponseCode.FAIL);
    }

    @Override
    public String getErrorPath() {
        log.error("errorPath....");
        return ERROR_PATH;
    }
}