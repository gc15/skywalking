/*
 * Copyright 2017, OpenSkywalking Organization All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Project repository: https://github.com/OpenSkywalking/skywalking
 */

package org.skywalking.apm.toolkit.log.logback.v1.x.mdc;

import ch.qos.logback.classic.pattern.MDCConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.util.OptionHelper;

/**
 * @author zhangkewei
 */
public class LogbackMDCPatternConverter extends MDCConverter {
    private static final  String CONVERT_KEY = "tid";

    private boolean convert4TID = false;
    @Override
    public void start() {
        super.start();
        String[] key = OptionHelper.extractDefaultReplacement(getFirstOption());
        if (null != key && key.length > 0 && CONVERT_KEY.equals(key[0])) {
            convert4TID = true;
        }
    }
    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return convert4TID ? convertTID() : super.convert(iLoggingEvent);
    }

    public String convertTID() {
        return "TID: N/A";
    }
}
