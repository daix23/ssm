package com.code.scheduler.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-30
 * Time: 上午10:35
 * To change this template use File | Settings | File Templates.
 */
public class SecondScheduledJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {
        System.out.println("I am SecondScheduledJob");

    }

}