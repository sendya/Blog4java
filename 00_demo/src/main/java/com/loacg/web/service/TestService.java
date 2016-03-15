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

package com.loacg.web.service;

import com.loacg.web.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Project: blog4java
 * Author: Sendya <18x@loacg.com>
 * Time: 2016/3/15 22:41
 */

@RestController
@RequestMapping("/test")
public class TestService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/time", method = RequestMethod.GET, headers = "Accept=application/json;charset=UTF-8")
    @ResponseBody
    public Response test(HttpServletRequest request,
                            HttpServletResponse response) {
        Response result = new Response();

        List<Object> list = new ArrayList<>();

        long nowTime = System.currentTimeMillis() / 1000;
        list.add(nowTime);

        String dbTime = jdbcTemplate.queryForObject("SELECT DATE_FORMAT(now(), '%Y-%m-%d %H:%i:%s')", String.class);
        list.add(dbTime);

        result.setData(list);
        result.setStatus("success", 0);

        return result;
    }

    @RequestMapping(value = "/add", headers = "Accept=application/json;charset=UTF-8")
    @ResponseBody
    @Transactional
    public Response add() {
        Response result = new Response();

        jdbcTemplate.update("INSERT INTO demo(str, `int`, unix_time, create_time, update_time) VALUES(?, ?, ?, ?, ?)",
                new Object[] {
                        "我是文本哈哈哈哈哈哈哈哈哈，不服咬我阿",
                        Integer.MAX_VALUE,
                        System.currentTimeMillis()/1000,
                        new Date(),
                        new Date()
                    });

        // 我不服，我要报错
        int t = 9/0; // 看你吖的还能插入到数据库么？ 让你吖的给我浪

        jdbcTemplate.update("INSERT INTO demo(str, `int`, unix_time, create_time, update_time) VALUES(?, ?, ?, ?, ?)",
                new Object[] {
                        "22222啊哈哈哈哈哈",
                        Integer.MAX_VALUE,
                        System.currentTimeMillis()/1000,
                        new Date(),
                        new Date()
                });

        result.setStatus("success", 0);

        return result;
    }
}
