package com.example.fsp.mapper;

import com.example.fsp.bean.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface CompanyMapper {
    @Select("SELECT * FROM company WHERE id=#{id}")
    @Results({
            @Result(property = "companyName", column = "name")
    })
    Company getCompanyById(Long id);
}
