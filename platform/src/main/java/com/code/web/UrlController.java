package com.code.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-28
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
public class UrlController {
    //@RequestMapping注解的方法，标记多个访问
    @RequestMapping(value={"/","/index"})
    public String index() {
        return "../../index";
    }

    //@RequestMapping注解的方法，标记多个访问
    @RequestMapping(value={"/map"})
    public String map() {
        return "map";
    }

}

