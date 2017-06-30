package com.code.scheduler;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-30
 * Time: 上午10:38
 * To change this template use File | Settings | File Templates.
 */
public class JobsTest {
    public static void main(String args[]){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("quartz-context.xml");
    }
}
