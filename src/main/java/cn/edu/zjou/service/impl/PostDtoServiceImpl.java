package cn.edu.zjou.service.impl;

import cn.edu.zjou.dto.PostDto;
import cn.edu.zjou.dto.PostStatusDto;
import cn.edu.zjou.enums.PostStatus;
import cn.edu.zjou.mapper.PostMapper;
import cn.edu.zjou.service.PostDtoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PostDtoServiceImpl implements PostDtoService {

    private final PostMapper postMapper;

    public PostDtoServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public PageInfo<PostDto> getPostDtosByPage(int page) {
        try {
            PageHelper.startPage(page, 20);
            List<PostDto> postDtos = postMapper.getAllPostDTOOrderByPublishDate();

            return new PageInfo<>(postDtos, 5);
        } finally {
            // 清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
    }

    @Override
    public List<PostStatusDto> getPostStatusDto() {
        Map<Integer, PostStatusDto> map = postMapper.getStatusCount();

        List<PostStatusDto> list = new ArrayList<>();
        for (var e : PostStatus.values()) {
            PostStatusDto dto = new PostStatusDto();
            dto.setStatusName(e.getStatusName());
            dto.setCount(map.get(e.getKey()).getCount());

            list.add(dto);
        }

        return list;
    }
}
