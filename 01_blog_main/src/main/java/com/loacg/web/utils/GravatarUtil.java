/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.loacg.web.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: blog4java
 * Author: Sendya <18x@loacg.com>
 * Time: 2016/3/16 0:15
 */
public class GravatarUtil {

    private static final List<String> HOSTS = new ArrayList<>();

    static {

        HOSTS.add("//gravatar0.ifdream.net/avatar/");
        HOSTS.add("//www.gravatar.com/avatar/");
        HOSTS.add("https://gravatar.css.network/avatar/");
        HOSTS.add("http://ruby-china.org/");
        HOSTS.add("http://gravatar.duoshuo.com/");
        HOSTS.add("https://gravatar.lug.ustc.edu.cn/avatar/");
    }

    public static String getGravatar(String email, String level, boolean img, Integer heightWidth) {
        StringBuffer url = new StringBuffer()
                .append(HOSTS.get(5));
        if(email == null && "".equals(email))
            return "";

        url.append(MD5Util.md5(email.toLowerCase()))
           .append("?s=$s&d=$d&r=$r");


        if(img) {
            if(heightWidth == null || heightWidth == 0)
                heightWidth = 128;

            String newUrl = "<img src=\"" + url.toString() + "\" width=\"" + heightWidth + "\" height=\"" + heightWidth + "\" />";
            return newUrl;
        }

        return url.toString();
    }


}
