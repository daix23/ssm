package com.code.service.impl;

import com.code.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-7-5
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ProducerServiceImpl implements ProducerService{
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 向指定队列发送消息
     */
    public void sendMessage(Destination destination, final String msg) {
        System.out.println("向队列" + destination.toString() + "发送了消息------------" + msg);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    /**
     * 向默认队列发送消息
     */
    public void sendMessage(final String msg) {
        String destination =  jmsTemplate.getDefaultDestination().toString();
        System.out.println("向队列" +destination+ "发送了消息------------" + msg);
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });

    }

}
