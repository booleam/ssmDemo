package com.zxs.ssm.controller;

import com.zxs.ssm.dao.TestDAO;
import com.zxs.ssm.model.ResponseDO;
import com.zxs.ssm.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asen on 16/5/11.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private TestDAO testDAO;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map testId(@PathVariable int id) {
        System.out.println(id);
        Map<String, String> map = new HashMap<>();
        map.put("k", "v");
        return map;
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDO testName() {
        ResponseDO responseDO = null;
        try {
            String name = testDAO.queryName();
            System.out.println(name);
            responseDO = new ResponseDO<>(200, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDO;
    }
}
