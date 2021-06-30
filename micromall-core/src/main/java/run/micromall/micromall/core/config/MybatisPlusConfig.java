package run.micromall.micromall.core.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * mybatisplus配置
 *
 * @author Administrator
 * @since 2021/4/27
 */
@Component
public class MybatisPlusConfig {
    /**
     * 攻击 SQL 阻断解析器,防止全表更新与删除
     *
     * @return

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }*/
}
