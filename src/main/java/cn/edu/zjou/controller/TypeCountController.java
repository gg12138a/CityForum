package cn.edu.zjou.controller;

import cn.edu.zjou.dto.TypeAndCountDto;
import cn.edu.zjou.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TypeCountController {

    private final TypeMapper typeMapper;

    public TypeCountController(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @GetMapping("/raw/typecount")
    public List<TypeAndCountDto> getTypeAndCount(){
        return typeMapper.getAllTypeAndCount();
    }
}
