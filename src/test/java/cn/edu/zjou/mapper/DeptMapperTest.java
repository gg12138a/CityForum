package cn.edu.zjou.mapper;

import cn.edu.zjou.dto.TypeAndDeptCountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DeptMapperTest {

    @Autowired
    private DeptMapper deptMapper;



    @Test
    public void test(){
        List<TypeAndDeptCountDto> test = deptMapper.getTypeAndDeptDtoList();
        System.out.println(test);
    }
}
