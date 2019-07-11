package com.example.mySource.abstractclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.mySource.entity.MstStudent;
import com.example.mySource.repository.MstStudentRepository;

public class AbstractService extends AbstractComponent {

    @Autowired
    private MstStudentRepository mstUserRepository;

    protected MstStudent getLoginUser(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return mstUserRepository.getOne(Integer.parseInt(authentication.getPrincipal().toString()));
    }
}
