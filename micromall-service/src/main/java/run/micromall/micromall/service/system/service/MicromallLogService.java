package run.micromall.micromall.service.system.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.micromall.micromall.db.system.mapper.MicromallLogMapper;
import run.micromall.micromall.db.system.model.entity.MicromallLog;
import run.micromall.micromall.db.system.model.enums.LogTypeEnum;

/**
 * <pre>
 * 日志记录 服务类
 * </pre>
 *
 * @author songhaozhi
 * @since 2021-07-04
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MicromallLogService {

    private final MicromallLogMapper micromallLogMapper;

    /**
     * 保存日志记录
     *
     * @author songhaozhi
     */
    @Transactional(rollbackFor = Throwable.class)
    @Async
    public int saveLog(String name, Long userId, String ip, String param,
                       String requestUrl, String referer, String ua, LogTypeEnum type) {
        MicromallLog micromallLog = new MicromallLog();
        micromallLog.setName(name);
        micromallLog.setId(userId);
        micromallLog.setIp(ip);
        micromallLog.setParam(param);
        micromallLog.setRequestUrl(requestUrl);
        micromallLog.setReferer(referer);
        micromallLog.setUa(ua);
        micromallLog.setType(type);
        return micromallLogMapper.insert(micromallLog);
    }

    /**
     * 通过ID修改日志记录
     *
     * @param micromallLog
     * @author songhaozhi
     */
    public int updateById(MicromallLog micromallLog) {
        return micromallLogMapper.updateById(micromallLog);
    }

    /**
     * 通过ID删除日志记录
     *
     * @param id
     * @author songhaozhi
     */
    public int deleteById(Long id) {
        return micromallLogMapper.deleteById(id);
    }

    /**
     * 根据ID获取查询日志记录
     *
     * @param id
     * @author songhaozhi
     */
    public MicromallLog selectById(Long id) {
        return micromallLogMapper.selectById(id);
    }

    /**
     * 分页获取日志记录
     *
     * @author songhaozhi
     */
    public PageInfo<MicromallLog> list(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        QueryWrapper<MicromallLog> wrapper = Wrappers.query();
        wrapper.eq("deleted", false);
        return new PageInfo<>(micromallLogMapper.list(wrapper));
    }

}