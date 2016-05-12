package com.zxs.ssm.service.impl;

import com.zxs.ssm.dao.TestDAO;
import com.zxs.ssm.model.ResponseDO;
import com.zxs.ssm.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by asen on 16/5/12.
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Resource
    private TestDAO testDAO;

    @Override
    public ResponseDO getName() throws Exception {
        return null;
    }
}
