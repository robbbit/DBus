/*-
 * <<
 * DBus
 * ==
 * Copyright (C) 2016 - 2019 Bridata
 * ==
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * >>
 */


package com.creditease.dbus.heartbeat.start;

import com.creditease.dbus.heartbeat.handler.IHandler;
import com.creditease.dbus.heartbeat.handler.impl.*;
import com.creditease.dbus.heartbeat.type.CommandType;
import com.creditease.dbus.heartbeat.type.LoggerType;
import com.creditease.dbus.heartbeat.util.Constants;
import org.apache.commons.lang.SystemUtils;

public class Start extends HeartBeat {

    static {
        // 获取存储log的基准path
        String logBasePath = System.getProperty(
                Constants.SYS_PROPS_LOG_BASE_PATH, SystemUtils.USER_DIR);
        // 设置log4j日志保存目录
        System.setProperty(Constants.SYS_PROPS_LOG_HOME,
                logBasePath.replaceAll("\\\\", "/") + "/logs/heartbeat");
        // 设置日志类型
        System.setProperty(Constants.SYS_PROPS_LOG_TYPE, LoggerType.HEART_BEAT.getName());
    }

    public static void main(String[] args) {
        Start start = new Start();
        start.run();
        start.startLifeCheck();
        CommandType.setTarget(start);
    }

    private void startLifeCheck() {
        IHandler handler = new LifeCycleHandler();
        handler.process();
    }

    @Override
    protected void register() {
        // 注册加载jdbc.properties和zk.properties配置信息handler
        registerHandler(new LoadFileConfigHandler());
        // 注册选举leader handler
        registerHandler(new LeaderElectHandler());
        // 注册加载zk配置信息
        registerHandler(new LoadZkConfigHandler());
        // 注册加载数据库配置信息handler
        registerHandler(new LoadDbConfigHandler());
        // 注册加载数据库配置信息handler
        registerHandler(new CreateZkNodeHandler());
        // 注册启动Kafka Consumer handler
        registerHandler(new KafkaConsumerHandler());

        // EventControl 启动多个线程包括：发送heartbeat, 检查heartbeat等
        // 注册启动event handler
        registerHandler(new EventControlHandler());
    }

    @Override
    public void start() {
        for (IHandler handler : handlers) {
            if (handler instanceof EventControlHandler)
                handler.process();
        }
    }
}
