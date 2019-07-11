package com.example.mySource.service;

import org.springframework.stereotype.Service;

import com.example.mySource.abstractclass.AbstractService;
import com.example.mySource.entity.MstStudent;

@Service
public class SampleService extends AbstractService {

    public MstStudent getLoginMstStudent(){
        MstStudent user = this.getLoginUser();
        log.debug(user.toString());
        return user;
    }

    public void log(String str){
        log.debug("サービスをよびだして出力したログ：　"+str);
    }
}
