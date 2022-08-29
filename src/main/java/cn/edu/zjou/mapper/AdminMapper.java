package cn.edu.zjou.mapper;

import cn.edu.zjou.po.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("""
        select a.*, d.dept_name
        from `t_admin`as a
        LEFT JOIN `t_dept` as d on a.dept_id = d.dept_id
        where username = #{username} and password = #{password}
    """)
    Admin getAdminByUsernamePassword(Admin admin);
}
