/*
 * micromall https://github.com/micromall-team/micromall
 *
 * Copyright (C) 2021-2021 micromall
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package run.micromall.micromall.core.aspect;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import run.micromall.micromall.core.annotation.Log;
import run.micromall.micromall.core.shiro.ShiroUtil;
import run.micromall.micromall.core.utils.AspectUtil;
import run.micromall.micromall.core.utils.RequestUtil;
import run.micromall.micromall.service.system.service.MicromallLogService;

import java.lang.reflect.Method;

/**
 * 日志切面，保存日志到数据库
 *
 * @author songhaozhi
 * @since 2021/7/4
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LogAspect {

    private final MicromallLogService logService;

    @Pointcut(value = "@annotation(run.micromall.micromall.core.annotation.Log)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object writeLog(ProceedingJoinPoint point) throws Throwable {
        //先执行业务
        Object result = point.proceed();
        handle(point);
        log.info("response -> {}", JSONUtil.toJsonStr(result));
        return result;
    }

    private void handle(ProceedingJoinPoint point) throws Exception {
        Method currentMethod = AspectUtil.INSTANCE.getMethod(point);
        //获取操作名称
        Log annotation = currentMethod.getAnnotation(Log.class);
        boolean save = annotation.save();
        Object[] args = point.getArgs();
        String name = AspectUtil.INSTANCE.parseParams(args, annotation.name());
        log.info("request -> {} | {} | {} - {} {} - {}", name, args, RequestUtil.getIp(),
                RequestUtil.getMethod(), RequestUtil.getRequestUrl(), RequestUtil.getUa());
        if (!save) {
            return;
        }

        logService.saveLog(name, ShiroUtil.getCurrentUser().getId(),RequestUtil.getIp()
                ,JSONUtil.toJsonStr(args),RequestUtil.getRequestUrl()
        ,RequestUtil.getReferer(),RequestUtil.getUa(),annotation.logType());
    }

}
