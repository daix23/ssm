package com.code.scheduler.jobs;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-30
 * Time: 上午10:26
 * To change this template use File | Settings | File Templates.
 */
@Component("myBean")
public class MyBean {
        public void printMessage() {
            System.out.println("I am MyBean. I am called by MethodInvokingJobDetailFactoryBean using SimpleTriggerFactoryBean");
        }
}
