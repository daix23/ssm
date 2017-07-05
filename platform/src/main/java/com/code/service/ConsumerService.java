package com.code.service;

import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-7-5
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
public interface ConsumerService {

    public TextMessage receive(Destination destination);

}
