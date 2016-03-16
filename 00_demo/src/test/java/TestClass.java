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

import org.pegdown.PegDownProcessor;
import org.pegdown.ast.RootNode;
import org.pegdown.plugins.PegDownPlugins;
import org.pegdown.plugins.ToHtmlSerializerPlugin;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Project: blog4java
 * Author: Sendya <18x@loacg.com>
 * Time: 2016/3/15 22:10
 */
public class TestClass {

    public static void main(String[] args)  {
        /*
        ClassPathXmlApplicationContext
                context = new ClassPathXmlApplicationContext("classpath:spring-db.xml");
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        */

        // jdbcTemplate.query("SELECT * FROM table WHERE id=?", new Object[] {1 });

        // markdown test
        String markdown = "## 我是H2  " +
                "**加粗** ~~删除~~  " +
                "hahha哈哈哈  " +
                "-----------" +
                "测试" +
                "===========" +
                "##### wtf?" +
                "`function(){}`";
        System.out.println(markdown);
        PegDownProcessor pegDownProcessor = new PegDownProcessor(0);
        String html = pegDownProcessor.markdownToHtml(markdown.toCharArray());
        System.out.println(html);

    }
}
