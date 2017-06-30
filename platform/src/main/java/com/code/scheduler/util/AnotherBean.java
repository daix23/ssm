package com.code.scheduler.util;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-30
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */
@Component("anotherBean")
public class AnotherBean {

    public void printAnotherMessage(){
        System.out.println("I am AnotherBean. I am called by Quartz jobBean using CronTriggerFactoryBean");
    }

}
