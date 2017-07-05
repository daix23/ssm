package com.code.service.impl;

import com.code.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-7-5
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private JmsTemplate jmsTemplate;
    /**
     * 接收消息
     */
    public TextMessage receive(Destination destination) {
        TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
        try {
            System.out.println("从队列" + destination.toString() + "收到了消息：\t"
                    + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return tm;

    }

}
