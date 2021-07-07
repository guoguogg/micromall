package run.micromall.micromall.core.annotation.support;

import cn.hutool.core.util.StrUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import run.micromall.micromall.core.annotation.LoginUser;
import run.micromall.micromall.service.user.manager.UserTokenManager;

/**
 * 自定义参数解析器
 *
 * @author songhaozhi
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public static final String LOGIN_TOKEN_KEY = "micromall-Token";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType()
                .isAssignableFrom(String.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) {
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        if (StrUtil.isEmpty(token)) {
            return null;
        }
        return UserTokenManager.getUserId(token);
    }

}
