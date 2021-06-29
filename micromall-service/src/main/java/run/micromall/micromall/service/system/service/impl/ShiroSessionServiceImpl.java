package run.micromall.micromall.service.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.micromall.micromall.db.system.mapper.ShiroSessionMapper;
import run.micromall.micromall.db.system.model.entity.ShiroSession;
import run.micromall.micromall.service.system.service.ShiroSessionService;

/**
 * ShiroSessionService
 *
 * @author Administrator
 * @since 2020/11/7
 */
@Service
public class ShiroSessionServiceImpl extends ServiceImpl<ShiroSessionMapper, ShiroSession> implements ShiroSessionService {
}
