package com.code;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-22
 * Time: 上午11:20
 * To change this template use File | Settings | File Templates.
 */
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class BaseTest {

}
