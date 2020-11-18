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


package com.creditease.dbus.heartbeat.resource.remote;

import com.creditease.dbus.heartbeat.container.CuratorContainer;
import com.creditease.dbus.heartbeat.resource.AbstractConfigResource;
import org.apache.curator.framework.CuratorFramework;

public abstract class ZkConfigResource<T> extends AbstractConfigResource<T> {

    protected CuratorFramework curator;

    protected ZkConfigResource(String name) {
        super(name);
    }

    protected void init() {
        curator = CuratorContainer.getInstance().getCurator();
    }

}
