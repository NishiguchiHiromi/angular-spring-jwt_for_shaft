package com.example.mySource.auth;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.mySource.entity.MstStudent;
import com.example.mySource.repository.MstStudentRepository;

@Component
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MstStudentRepository MstStudentRepository;
    @Override
    public UserDetails loadUserByUsername(String mailAddress)
            throws UsernameNotFoundException {

        // 認証を行うユーザー情報を格納する
        MstStudent user = MstStudentRepository.findByMailAddress(mailAddress)
        		.stream().findFirst()
                // ユーザー情報を取得できなかった場合
                .orElseThrow(() -> new UsernameNotFoundException("User not found for login id: " + mailAddress));

        // ユーザー情報が取得できたらSpring Securityで認証できる形で戻す
        return new LoginUserDemo(user);
    }

}
