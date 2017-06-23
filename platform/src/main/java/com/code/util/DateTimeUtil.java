package com.code.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 13-12-3
 * Time: 上午11:17
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeUtil {

    //MM是月份，mm是分；HH是24小时制，而hh是12小时制
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    private static SimpleDateFormat sdf5 = new SimpleDateFormat("yyyyMMddHHmmssSSS");


    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date strToDate(String str){
        Date date = null;
        try{
            if(str.indexOf("年")!=-1&&str.indexOf("月")!=-1&&str.indexOf("日")!=-1){
              str=str.replace("年","-").replace("月","-").replace("日","");
            }
            if(str.indexOf(":")==-1){
                str += " 00:00:00";
            }
            date  = sdf1.parse(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date strToDate4(String str){
        Date date = null;
        try{
            date  = sdf4.parse(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date strToDate2(String str){
        Date date = null;
        try{
            date  = sdf2.parse(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {
        String str = sdf1.format(date);
        return str;
    }

    public static String DateToStr4(Date date) {
        String str = sdf4.format(date);
        return str;
    }

    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String DateToStr2(Date date) {
        String str = sdf5.format(date);
        return str;
    }
    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String DateToStr3(Date date) {
        String str = sdf2.format(date);
        return str;
    }
    /**
     * 毫秒转换成字符串日期
     * @param timeMillis
     * @return str
     */
    public static String timeMillisToStr(long timeMillis) {
        String str = sdf1.format(timeMillis);
        return str;
    }

    /**
     * DATE转换成毫秒
     * @param date
     * @return timeMillis
     */
    public static long dateToTimeMillis(Date date) {
        long timeMillis = date.getTime();
        return timeMillis;
    }

    public static void main(String[] args){
//        long ll = 1395018334394L;
//        String str = DateTimeUtil.timeMillisToStr(ll);
//        System.out.println(str);
//        Date date = DateTimeUtil.strToDate(str);
//        long lg = DateTimeUtil.dateToTimeMillis(date);
//        System.out.println(lg);

        /*String start="2013年10月";
        String end="2015年12月";
        List list =DateTimeUtil.getAllMonths(start, end);
        for (int i=0;i<list.size();i++) {
            System.out.println(list.get(i));
        }*/
        String time = "2015-06-23 15:41:27";
        long date  = strToDate(time).getTime();
        System.out.println(date);

    }

    public static List getAllMonths(String start, String end){
        String splitSign="年";
        String regex="\\d{4}"+splitSign+"(([0][1-9])|([1][012]))"+"月"; //判断YYYY-MM时间格式的正则表达式
        if(!start.matches(regex) || !end.matches(regex)){
            return null;
        }

        List<String> list=new ArrayList<String>();
        if(start.compareTo(end)>0){
            //start大于end日期时，互换
            String temp=start;
            start=end;
            end=temp;
        }
        list.add("全部");
        String temp=start; //从最小月份开始
        while(temp.compareTo(start)>=0 && temp.compareTo(end)<=0){
            list.add(temp); //首先加上最小月份,接着计算下一个月份
            String[] arr=temp.split(splitSign);
            String monthStr = arr[1].substring(0,arr[1].indexOf("月"));
            int year=Integer.valueOf(arr[0]);
            int month=Integer.valueOf(monthStr)+1;
            if(month>12){
                month=1;
                year++;
            }
            if(month<10){//补0操作
                temp=year+splitSign+"0"+month+"月";
            }else{
                temp=year+splitSign+month+"月";
            }
        }
        return list;
    }




}
