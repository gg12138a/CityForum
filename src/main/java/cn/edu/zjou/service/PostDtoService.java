package cn.edu.zjou.service;

import cn.edu.zjou.dto.PostDto;
import cn.edu.zjou.dto.PostStatusDto;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface PostDtoService {

    PageInfo<PostDto> getPostDtosByPage(int page);

    List<PostStatusDto> getPostStatusDto();
}
