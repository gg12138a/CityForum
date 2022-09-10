package cn.edu.zjou.controller;


import cn.edu.zjou.dto.PostDto;
import cn.edu.zjou.dto.PostStatusDto;
import cn.edu.zjou.dto.NameAndValueDto;
import cn.edu.zjou.dto.TypeAndDeptCountDto;
import cn.edu.zjou.mapper.DeptMapper;
import cn.edu.zjou.mapper.TypeMapper;
import cn.edu.zjou.service.PostService;
import cn.edu.zjou.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RawController {

    private final PostService postService;
    private final DeptMapper deptMapper;
    private final TypeMapper typeMapper;

    public RawController(PostService postService, DeptMapper deptMapper, TypeMapper typeMapper) {
        this.postService = postService;
        this.deptMapper = deptMapper;
        this.typeMapper = typeMapper;
    }


    @GetMapping("/raw/notice/{page}")
    public PageInfo<PostDto> getNotice(@PathVariable(value = "page") Integer page) {

        return postService.getPostDtosByPage(page);
    }

    @GetMapping("/raw/summary")
    public List<PostStatusDto> getSummary() {
        return postService.getPostStatusDto();
    }

    @GetMapping("/raw/mention")
    public ResponseVo getMention() {
        List<TypeAndDeptCountDto> typeAndDeptDtoList = deptMapper.getTypeAndDeptDtoList();

        List<String> deptNameList = typeAndDeptDtoList.stream()
                .findFirst()
                .get()
                .getDeptAndCountList()
                .stream()
                .map(TypeAndDeptCountDto.DeptAndCount::getDeptName)
                .toList();

        List<String> typeNameList = typeAndDeptDtoList.stream()
                .map(TypeAndDeptCountDto::getTypeName)
                .toList();


        Map<String, Object> map = new HashMap<>();
        map.put("typeNameList", typeNameList);
        map.put("deptNameList", deptNameList);
        map.put("dataList", typeAndDeptDtoList);

        return ResponseVo.returnOk(map);
    }

    @GetMapping("/raw/typecount")
    public List<NameAndValueDto> getTypeAndCount(){
        return typeMapper.getAllTypeAndCount();
    }
}
