package cn.edu.zjou.service;

import cn.edu.zjou.dto.PostDto;
import cn.edu.zjou.dto.PostStatusDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PostService {

    PageInfo<PostDto> getPostDtosByPageByDept(int page);

    PageInfo<PostDto> getComplainPostDtosByPage(int page);

    PageInfo<PostDto> getPostDtosByPage(int page);

    List<PostStatusDto> getPostStatusDtoByDept();

    List<PostStatusDto> getPostStatusDto();
}
