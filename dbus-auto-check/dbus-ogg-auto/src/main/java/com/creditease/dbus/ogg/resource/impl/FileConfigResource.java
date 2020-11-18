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


package com.creditease.dbus.ogg.resource.impl;

import com.creditease.dbus.ogg.bean.ConfigBean;
import com.creditease.dbus.ogg.resource.AbstractConfigResource;
import org.apache.commons.lang3.StringUtils;

/**
 * User: 王少楠
 * Date: 2018-08-24
 * Desc:
 */
public class FileConfigResource extends AbstractConfigResource<ConfigBean> {

    public FileConfigResource(String name) {
        super(name);
    }

    public ConfigBean parse() {
        ConfigBean config = new ConfigBean();
        try {
            if (!StringUtils.isBlank(props.getProperty("tables.append"))) {
                String[] appendTables = props.getProperty("ables.append").trim().split(",");
                config.setAppendTables(appendTables);
                config.setDsName(props.getProperty("dsname").trim());
                config.setOggBigHome(props.getProperty("ogg.big.home").trim());
            } else {
                config.setDsName(props.getProperty("dsname").trim());
                config.setOggBigHome(props.getProperty("ogg.big.home").trim());
                config.setOggUrl(props.getProperty("ogg.url").trim());
                config.setOggUser(props.getProperty("ogg.user").trim());
                config.setOggPwd(props.getProperty("ogg.pwd").trim());
                config.setKafkaUrl(props.getProperty("kafka.url"));
                String kafkaProducerName = props.getProperty("kafka.producer.name").trim();
                if (!kafkaProducerName.endsWith(".properties")) {
                    kafkaProducerName = kafkaProducerName + ".properties";
                }
                config.setKafkaProducerName(kafkaProducerName);
                //首次添加，需要将默认的table添加
                String tables = props.getProperty("tables").trim();
                tables += ",DBUS.DB_HEARTBEAT_MONITOR" +
                        ",DBUS.META_SYNC_EVENT";
                config.setTables(tables.toString().split(","));
                config.setNlsLang(props.getProperty("nls.lang").trim());
            }
        } catch (Exception e) {
            System.out.println("load props error.: " + name);
            throw e;
        }
        return config;
    }

}
