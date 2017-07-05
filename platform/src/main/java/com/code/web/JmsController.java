package com.code.web;

import com.code.dto.Result;
import com.code.entity.Users;
import com.code.service.ConsumerService;
import com.code.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-7-5
 * Time: 上午10:35
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/jms")
public class JmsController {

    //队列名queue.demo
    @Autowired
    @Qualifier("demoQueueDestination")
    private Destination demoQueueDestination;

    //队列消息生产者
    @Autowired
    private ProducerService producer;

    //队列消息消费者
    @Autowired
    private ConsumerService consumer;


    @RequestMapping(value="/welcome",method=RequestMethod.GET)
    public String welcome(){
        System.out.println("JMS-------welcome");
        return "jms_welcome";
    }

    @RequestMapping(value="/producer",method=RequestMethod.GET)
    public ModelAndView producer(){
        System.out.println("------------go producer");
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format( now );
        System.out.println(time);

        ModelAndView mv = new ModelAndView();
        mv.addObject("time", time);
        mv.setViewName("jms_producer");
        return mv;
    }

    @RequestMapping(value="/onsend",method=RequestMethod.POST)
    public ModelAndView producer(@RequestParam("message") String message) {
        System.out.println("------------send to jms");
        ModelAndView mv = new ModelAndView();
        producer.sendMessage(demoQueueDestination, message);
        mv.setViewName("jms_welcome");
        return mv;
    }

    @RequestMapping(value="/receive",method=RequestMethod.GET)
    public ModelAndView queue_receive() throws JMSException {
        System.out.println("------------receive message");
        ModelAndView mv = new ModelAndView();

        TextMessage tm = consumer.receive(demoQueueDestination);
        mv.addObject("textMessage", tm.getText());

        mv.setViewName("queue_receive");
        return mv;
    }

    /*
     * ActiveMQ Manager Test
     */
    @RequestMapping(value="/jms",method=RequestMethod.GET)
    public ModelAndView jmsManager() throws IOException {
        System.out.println("------------jms manager");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jms_welcome");

        JMXServiceURL url = new JMXServiceURL("");
        JMXConnector connector = JMXConnectorFactory.connect(url);
        connector.connect();
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        return mv;
    }


}
