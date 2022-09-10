package cn.edu.zjou.controller;

import cn.edu.zjou.dto.*;
import cn.edu.zjou.enums.PostStatus;
import cn.edu.zjou.mapper.DeptMapper;
import cn.edu.zjou.mapper.TypeMapper;
import cn.edu.zjou.service.PostService;
import cn.edu.zjou.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ComplaintsController {

    private final PostService postService;
    private final TypeMapper typeMapper;
    private final DeptMapper deptMapper;

    public ComplaintsController(PostService postService, TypeMapper typeMapper, DeptMapper deptMapper) {
        this.postService = postService;
        this.typeMapper = typeMapper;
        this.deptMapper = deptMapper;
    }

    @GetMapping("/complain/notice/{page}")
    public PageInfo<PostDto> getNotice(@PathVariable(value = "page") Integer page) {

        return postService.getComplainPostDtosByPage(page);
    }

    @GetMapping("/complain/mention")
    public ResponseVo getComplainDeptMention() {
        Long typeIdOfComplain = typeMapper.getTypeIdOfComplain();
        List<TypeAndDeptCountDto.DeptAndCount> deptAndCountList = deptMapper.getDeptAndCountList(typeIdOfComplain);

        List<String> deptNameList = deptAndCountList.stream()
                .map(TypeAndDeptCountDto.DeptAndCount::getDeptName)
                .toList();

        Map<String, Object> map = new HashMap<>();
        map.put("typeNameList", new ArrayList<String>() {{
            this.add("投诉");
        }});
        map.put("deptNameList", deptNameList);

        map.put("dataList", new ArrayList<TypeAndDeptCountDto>() {{
            this.add(new TypeAndDeptCountDto("投诉", deptAndCountList));
        }});

        return ResponseVo.returnOk(map);
    }

    @GetMapping("/complain/status")
    public List<NameAndValueDto> getStatusAndCountOfComplain() {
        List<StatusIdAndCountDto> statusCountOfComplain = typeMapper.getStatusCountOfComplain();

        return statusCountOfComplain.stream()
                .map(statusIdAndCountDto -> {
                    Long statusId = statusIdAndCountDto.getStatusId();
                    Long count = statusIdAndCountDto.getValue();

                    PostStatus postStatus = PostStatus.getPostStatusByKey(Math.toIntExact(statusId));

                    return new NameAndValueDto(postStatus.getStatusName(), count);
                })
                .toList();
    }
}
