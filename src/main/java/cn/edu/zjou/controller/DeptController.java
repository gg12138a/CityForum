package cn.edu.zjou.controller;

import cn.edu.zjou.dto.PostDto;
import cn.edu.zjou.dto.PostStatusDto;
import cn.edu.zjou.service.PostDtoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    private final PostDtoService postDtoService;

    public DeptController(PostDtoService postDtoService) {
        this.postDtoService = postDtoService;
    }

    @GetMapping("/dept/notice/{page}")
    public PageInfo<PostDto> getDeptNotice(@PathVariable(value = "page") Integer page) {

        return postDtoService.getPostDtosByPageByDept(page);
    }

    @GetMapping("/dept/summary")
    public List<PostStatusDto> getDeptSummary() {
        return postDtoService.getPostStatusDtoByDept();
    }
}
