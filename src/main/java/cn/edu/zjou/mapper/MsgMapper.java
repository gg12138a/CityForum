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

}
