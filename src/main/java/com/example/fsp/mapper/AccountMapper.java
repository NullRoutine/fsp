package com.example.fsp.mapper;

import com.example.fsp.bean.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountMapper {
    @Select("SELECT * FROM account WHERE user_id=#{userId}")
    @Results({
            @Result(property = "accountName", column = "name")
    })
    List<Account> getAccountByUserId(Long userId);
}
