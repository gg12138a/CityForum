package cn.edu.zjou.mapper;

import cn.edu.zjou.dto.PostDto;
import cn.edu.zjou.dto.PostStatusDto;
import cn.edu.zjou.po.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PostMapper extends BaseMapper<Post> {

    @Select("""
        SELECT p.post_id, p.title, p.reply_count, p.check_count, p.publish_date,
        	u.username as publisherName,
        	d.dept_name,
        	t.type_name
        FROM `t_post` AS p
        LEFT JOIN `t_user` AS u ON p.publish_user_id = u.user_id
        LEFT JOIN `t_dept` AS d ON p.dept_id = d.dept_id
        LEFT JOIN `t_type` AS t ON p.type_id = t.type_id
        ORDER BY p.publish_date DESC
    """)
    public List<PostDto> getAllPostDTOOrderByPublishDate();

    @Select("""
        SELECT t.post_status as 'key', COUNT(1) as 'count'
        FROM `t_post` AS t
        GROUP BY t.post_status
    """)
    @MapKey("key")
    Map<Integer,PostStatusDto> getStatusCount();
}
