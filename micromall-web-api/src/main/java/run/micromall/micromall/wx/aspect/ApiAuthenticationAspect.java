package run.micromall.micromall.wx.aspect;

import cn.hutool.core.util.ObjectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import run.micromall.micromall.core.annotation.PassToken;
import run.micromall.micromall.core.utils.AspectUtil;
import run.micromall.micromall.service.utils.ResponseUtil;

import java.lang.reflect.Method;

/**
 * @author Administrator
 */
@Aspect
@Component
public class ApiAuthenticationAspect {

    /**
     * 定义切入点
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * run.micromall.micromall.wx.*.controller..*.*(..)))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Method currentMethod = AspectUtil.INSTANCE.getMethod(point);
        //检查是否有PassToken注解，有则跳过认证
        if (currentMethod.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = currentMethod.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return point.proceed();
            }
        }
        //如果没有则执行认证
        //通过joinPoint.getArgs()获取Args参数
        Object[] args = point.getArgs();
        if (ObjectUtil.isNull(args[0]) || !(args[0] instanceof String)) {
            return ResponseUtil.notLogin();
        }
        return point.proceed();
    }
}