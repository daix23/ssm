package com.code.scheduler.jobs;

import com.code.scheduler.util.AnotherBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-30
 * Time: 上午10:34
 * To change this template use File | Settings | File Templates.
 */
public class FirstScheduledJob extends QuartzJobBean {

    private AnotherBean anotherBean;

    @Override
    protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {
        System.out.println("I am FirstScheduledJob");
        this.anotherBean.printAnotherMessage();

    }

    public void setAnotherBean(AnotherBean anotherBean) {
        this.anotherBean = anotherBean;
    }
}
