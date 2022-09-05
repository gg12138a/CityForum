package cn.edu.zjou.controller;

import cn.edu.zjou.dto.PostDto;
import cn.edu.zjou.dto.PostStatusDto;
import cn.edu.zjou.mapper.DeptMapper;
import cn.edu.zjou.service.PostService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    private final PostService postService;

    public DeptController(PostService postService, DeptMapper deptMapper) {
        this.postService = postService;
    }

    @GetMapping("/dept/notice/{page}")
    public PageInfo<PostDto> getDeptNotice(@PathVariable(value = "page") Integer page) {

        return postService.getPostDtosByPageByDept(page);
    }

    @GetMapping("/dept/summary")
    public List<PostStatusDto> getDeptSummary() {
        return postService.getPostStatusDtoByDept();
    }
}
