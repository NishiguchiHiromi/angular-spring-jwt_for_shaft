package com.example.mySource.auth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.mySource.entity.MstStudent;

public class LoginUserDemo extends User {

    private MstStudent MstStudent;

    public LoginUserDemo(MstStudent user){
        // スーパークラスのユーザーID、パスワードに値をセットする
        // 実際の認証はスーパークラスのユーザーID、パスワードで行われる
        super(user.getMailAddress(), user.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.MstStudent = user;
    }

    public MstStudent getMstStudent(){
        return this.MstStudent;
    }


}
