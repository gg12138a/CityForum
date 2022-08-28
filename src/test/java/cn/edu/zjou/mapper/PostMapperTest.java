package cn.edu.zjou.mapper;

import cn.edu.zjou.dto.PostDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostMapperTest {

    @Autowired
    private PostMapper postMapper;

    @Test
    public void testGetAllPostDTOOrderByPublishDate() {
        List<PostDto> postDtos = postMapper.getAllPostDTOOrderByPublishDate();

        System.out.println(postDtos.size());
        System.out.println(postDtos.get(0));
    }

    @Test
    public void testPagination() {

        PageInfo<PostDto> pageInfo;

        try {
            PageHelper.startPage(2, 20);
            List<PostDto> postDtos = postMapper.getAllPostDTOOrderByPublishDate();

            pageInfo = new PageInfo<>(postDtos, 5);

        } finally {
            // 清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
    }

    @Test
    public void testGetStatusCount(){
        System.out.println(postMapper.getStatusCount());
    }
}
