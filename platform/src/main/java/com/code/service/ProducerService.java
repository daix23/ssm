package com.code.service;

import javax.jms.Destination;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-7-5
 * Time: 上午10:29
 * To change this template use File | Settings | File Templates.
 */
public interface ProducerService {

    public void sendMessage(Destination destination, final String message);

    public void sendMessage(final String message);

}
