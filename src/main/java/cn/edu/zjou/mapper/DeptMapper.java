package cn.edu.zjou.mapper;

import cn.edu.zjou.dto.TypeAndDeptCountDto;
import cn.edu.zjou.po.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper extends BaseMapper<Dept> {

    @Select("""
    SELECT t.type_id, t.type_name FROM `t_type` as t
    ORDER BY t.type_id
    """)
    @Results(id = "companyResultMap", value = {
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "deptAndCountList", column = "type_id", many = @Many(select = "getDeptAndCountList"))
    })
    public List<TypeAndDeptCountDto> getTypeAndDeptDtoList();


    // 之前的错误写法：
    //    select d.dept_name, count(1) as `count`
    //    from `t_dept` as d
    //    LEFT JOIN `t_post` as p on p.`dept_id` = d.`dept_id`
    //    where p.type_id = #{typeId}
    //    group by d.dept_id
    //    order by d.dept_id;
    @Results({
            @Result(property = "employeeId", column = "EMPLOYEE_ID"),
            @Result(property = "employeeName", column = "EMPLOYEE_NAME")
    })
    @Select("""
    SELECT d.dept_name, COUNT(p.dept_id) AS `count`,d.dept_id
    FROM `t_dept` AS d
    LEFT JOIN (
        SELECT * FROM `t_post` AS p1 WHERE p1.type_id =#{typeId}
    ) AS p
    ON p.`dept_id` = d.`dept_id`
    GROUP BY d.dept_id
    ORDER BY d.dept_id;
    """)
    public List<TypeAndDeptCountDto.DeptAndCount> getDeptAndCountList(@Param("typeId") Long typeId);
}
