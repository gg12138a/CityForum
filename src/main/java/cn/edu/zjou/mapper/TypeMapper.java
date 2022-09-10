package cn.edu.zjou.mapper;

import cn.edu.zjou.dto.NameAndValueDto;
import cn.edu.zjou.dto.PostStatusDto;
import cn.edu.zjou.dto.StatusIdAndCountDto;
import cn.edu.zjou.po.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TypeMapper extends BaseMapper<Type> {

    @Select("""
            SELECT t.type_name as name, COUNT(1) as value
            FROM `t_post` as p
            LEFT JOIN `t_type` as t ON p.type_id = t.type_id
            GROUP BY t.type_id
            """)
    List<NameAndValueDto> getAllTypeAndCount();

    @Select("""
            SELECT p.post_status as statusId, COUNT(1) as value
            FROM `t_post` AS p
            LEFT JOIN `t_type` AS t ON p.type_id = t.type_id
            WHERE t.type_name='投诉'
            GROUP BY p.post_status
        """)
    List<StatusIdAndCountDto> getStatusCountOfComplain();

    @Select("""
            SELECT t.type_id
            FROM `t_type` as t 
            WHERE t.type_name='投诉'
            """)
    Long getTypeIdOfComplain();
}
