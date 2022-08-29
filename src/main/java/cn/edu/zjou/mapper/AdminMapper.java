package cn.edu.zjou.mapper;

import cn.edu.zjou.po.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("""
        select *
        from `t_admin`
        where username = #{username} and password = #{password}
    """)
    Admin getAdminByUsernamePassword(Admin admin);
}
