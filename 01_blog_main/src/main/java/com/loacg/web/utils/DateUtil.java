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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Project: To Love Project
 * Author: Sendya <18x@loacg.com>
 * Time: 2014/05/20 20:14
 *
 * UtilName: 时间日期操作类
 * Version: 1.0.0.1109
 */
public class DateUtil {

    static {
        TimeZone time = TimeZone.getTimeZone("GMT+8"); //设置为东八区
        TimeZone.setDefault(time);// 设置时区
    }

    public static void main(String[] args) {

        System.out.println("当天24点时间：" + date2String(getTimesnight()));
        System.out.println("当前时间：" + date2String(new Date()));
        System.out.println("当天0点时间：" + date2String(getTimesmorning()));
        System.out.println("昨天0点时间：" + date2String(getYesterdaymorning()));
        System.out.println("近7天时间：" + date2String(getWeekFromNow()));
        System.out.println("本周周一0点时间：" + date2String(getTimesWeekmorning()));
        System.out.println("本周周日24点时间：" + date2String(getTimesWeeknight()));
        System.out.println("本月初0点时间：" + date2String(getTimesMonthmorning()));
        System.out.println("本月未24点时间：" + date2String(getTimesMonthnight()));
        System.out.println("上月初0点时间：" + date2String(getLastMonthStartMorning()));
        System.out.println("本季度开始点时间：" + date2String(getCurrentQuarterStartTime()));
        System.out.println("本季度结束点时间：" + date2String(getCurrentQuarterEndTime()));
        System.out.println("本年开始点时间：" + date2String(getCurrentYearStartTime()));
        System.out.println("本年结束点时间：" + date2String(getCurrentYearEndTime()));
        System.out.println("上年开始点时间：" + getLastYearStartTime());

    }

    /**
     * 时间格式化
     *      时间字符串格式化为新的时间格式文本
     * @param date
     * @param oldFormat
     * @param newFormat
     * @return
     */
    public static String format(String date, String oldFormat, String newFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
        String d = null;
        try {

            Date dateObj = sdf.parse(date);
            SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);
            d = sdf2.format(dateObj);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }


    public static String date2String(Date date) {
        return DateUtil.date2String(date, "yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 时间对象转字符串
     * @param date
     * @param format
     * @return
     */
    public static String date2String(Date date, String format) {
        if(format==null || format.equals("")) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 字符串时间 转换 为时间对象
     *
     * @param date <b>时间文本字符串</b>
     * @param oldFormat <b>时间文本格式</b> 例：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date str2Date(String date, String oldFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
        Date d = null;
        try {

            d = sdf.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 传入时间进行时间操作（改变天/周/月/年）
     * 并返回时间对象
     *
     * @param date   Date 对象
     * @param unit   <b>提示:</b> 1 = 改变年，2 = 改变月，3 = 改变周，5 = 改变天
     * @param number <b>要改变的时间数值</b>
     * @return
     */
    public static Date dateChange(Date date, Integer unit, Integer number) {
        GregorianCalendar gc = new GregorianCalendar();
        /**
         * gc.add(1,-1)表示年份减一.
         * gc.add(2,-1)表示月份减一.
         * gc.add(3.-1)表示周减一.
         * gc.add(5,-1)表示天减一.
         */
        try {
            gc.setTime(date);
            gc.add(unit, number);
        } catch (Exception e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return gc.getTime();

    }

    /**
     * 对传入的时间字符串进行时间操作（改变天/周/月/年）
     * 并返回时间对象
     *
     * @param date   <b>传入的时间字符串</b> 例如：2015-01-01 12:12:59
     * @param format <b>传入的时间格式</b> 例如：yyyy-MM-dd HH:mm:ss
     * @param unit   <b>提示:</b> 1 = 改变年，2 = 改变月，3 = 改变周，5 = 改变天
     * @param number <b>要改变的时间数值</b>
     * @return
     */
    public static Date dateChange(String date, String format, Integer unit, Integer number) {
        GregorianCalendar gc = new GregorianCalendar();
        try {
            gc.setTime(new SimpleDateFormat(format).parse(date));
            gc.add(unit, number);
            //备注
            /**
             * gc.add(1,-1)表示年份减一.
             * gc.add(2,-1)表示月份减一.
             * gc.add(3.-1)表示周减一.
             * gc.add(5,-1)表示天减一.
             */
        } catch (Exception e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return gc.getTime();
    }

    /**
     * 获得当天0点时间
     *
     * @return
     */
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得昨天0点时间
     *
     * @return
     */
    public static Date getYesterdaymorning() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesmorning().getTime() - 3600 * 24 * 1000);
        return cal.getTime();
    }

    /**
     * 获得当天近7天时间
     *
     * @return
     */
    public static Date getWeekFromNow() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesmorning().getTime() - 3600 * 24 * 1000 * 7);
        return cal.getTime();
    }

    /**
     * 获得当天24点时间
     *
     * @return
     */
    public static Date getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得本周一0点时间
     *
     * @return
     */
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获得本周日24点时间
     *
     * @return
     */
    public static Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }

    /**
     * 获得本月第一天0点时间
     *
     * @return
     */
    public static Date getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获得本月最后一天24点时间
     *
     * @return
     */
    public static Date getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }

    /**
     * 获得上月初0点时间
     *
     * @return
     */
    public static Date getLastMonthStartMorning() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesMonthmorning());
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获得当前季度的开始时间，即2015-01-01 00:00:00
     *
     * @return
     */
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2015-03-31 23:59:59
     *
     * @return Date
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentQuarterStartTime());
        cal.add(Calendar.MONTH, 3);
        return cal.getTime();
    }

    /**
     * 获得本年开始点时间
     *
     * @return
     */
    public static Date getCurrentYearStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.YEAR));
        return cal.getTime();
    }

    /**
     * 获得本年结束点时间
     *
     * @return
     */
    public static Date getCurrentYearEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentYearStartTime());
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

    /**
     * 获得上年开始点时间
     *
     * @return
     */
    public static Date getLastYearStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentYearStartTime());
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }

    /**
     * 获取当前 unix 时间
     *
     * @return Long
     */
    public static long getTimeStamp() {
        return System.currentTimeMillis()/1000;
    }

    /**
     * 获取当前 unix 时间
     * 传递 <b>milliseconds</b> 参数为真则保留到微秒
     * @param milliseconds
     * @return
     */
    public static long getTimeStamp(boolean milliseconds) {
        long time = System.currentTimeMillis();
        if(!milliseconds)
            time = time/1000;
        return time;
    }

    public static String TimeStamp2Date(long timeStamp, String format) {

        if(timeStamp < 10000000000L) timeStamp*=1000;
        String date = new SimpleDateFormat(format).format(new Date(timeStamp));
        return date;
    }

    public static Date TimeStamp2Date(long timeStamp) {
        if(timeStamp < 10000000000L) timeStamp*=1000;
        return new Date(timeStamp);
    }
}
