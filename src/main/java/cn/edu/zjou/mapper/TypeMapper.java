package cn.edu.zjou.mapper;

import cn.edu.zjou.dto.TypeAndCountDto;
import cn.edu.zjou.po.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapper extends BaseMapper<Type> {

    @Select("""
            SELECT t.type_name as name, COUNT(1) as value
            FROM `t_post` as p
            LEFT JOIN `t_type` as t ON p.type_id = t.type_id
            GROUP BY t.type_id
            """)
    List<TypeAndCountDto> getAllTypeAndCount();
}
