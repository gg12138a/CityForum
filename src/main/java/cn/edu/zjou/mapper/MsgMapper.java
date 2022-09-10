package cn.edu.zjou.mapper;

import cn.edu.zjou.po.Msg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MsgMapper extends BaseMapper<Msg> {


    @Select("""
                SELECT m.content
                FROM `t_msg` as m
                WHERE release_date>=#{start} and release_date<#{end}
            """)
    List<String> getAllContent(@Param("start") Date start, @Param("end") Date end);

    @Select("""
            SELECT m.content
            FROM `t_msg` AS m
            LEFT JOIN `t_post` AS p ON m.post_id = p.post_id
            LEFT JOIN `t_type` AS t ON p.type_id = t.type_id
            WHERE t.type_name = '投诉' and m.release_date>=#{start} and m.release_date<#{end}
            """)
    List<String> getAllContentOfComplain(@Param("start") Date start, @Param("end") Date end);


}
