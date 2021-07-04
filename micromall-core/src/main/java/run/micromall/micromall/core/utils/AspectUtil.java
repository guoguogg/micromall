package run.micromall.micromall.core.utils;

import cn.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.List;

/**
 * AOP工具类
 */
public enum AspectUtil {
    /**
     * 单例对象
     */
    INSTANCE;

    /**
     * 获取当前切面执行的方法的方法名
     *
     * @param point 当前切面执行的方法
     */
    public Method getMethod(JoinPoint point) throws NoSuchMethodException {
        Signature sig = point.getSignature();
        MethodSignature msig = (MethodSignature) sig;
        Object target = point.getTarget();
        return target.getClass()
                .getMethod( msig.getName(), msig.getParameterTypes() );
    }

    public String parseParams(Object[] params, String bussinessName) {
        if (bussinessName.contains("{") && bussinessName.contains("}")) {
            List<String> result = RegexUtils.match(bussinessName, "(?<=\\{)(\\d+)");
            for (String s : result) {
                int index = Integer.parseInt(s);
                bussinessName = bussinessName.replaceAll("\\{" + index + "}", JSONUtil.toJsonStr(params[index - 1]));
            }
        }
        return bussinessName;
    }

}
