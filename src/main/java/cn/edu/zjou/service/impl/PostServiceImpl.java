package cn.edu.zjou.service.impl;

import cn.edu.zjou.dto.PostDto;
import cn.edu.zjou.dto.PostStatusDto;
import cn.edu.zjou.enums.PostStatus;
import cn.edu.zjou.mapper.PostMapper;
import cn.edu.zjou.po.Admin;
import cn.edu.zjou.service.PostService;
import cn.edu.zjou.utils.ThreadContextUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public PageInfo<PostDto> getPostDtosByPageByDept(int page) {
        Admin admin = ThreadContextUtil.getThreadLocalAdminUser();

        try {
            PageHelper.startPage(page, 20);
            List<PostDto> postDtos = postMapper.getAllPostDTOOrderByPublishDateByDept(admin.getDeptId());

            return new PageInfo<>(postDtos, 5);
        } finally {
            // 清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
    }

    @Override
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
    public List<PostStatusDto> getPostStatusDtoByDept() {
        Admin admin = ThreadContextUtil.getThreadLocalAdminUser();
        Map<Integer, PostStatusDto> map = postMapper.getStatusCountByDept(admin.getDeptId());

        List<PostStatusDto> list = new ArrayList<>();
        for (var e : PostStatus.values()) {
            PostStatusDto dto = new PostStatusDto();
            dto.setStatusName(e.getStatusName());

            PostStatusDto dtoInMap = map.get(e.getKey());
            dto.setCount(dtoInMap ==null?0:dtoInMap.getCount());

            list.add(dto);
        }

        return list;
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
