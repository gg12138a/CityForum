package cn.edu.zjou.mapper;

import cn.edu.zjou.dto.NameAndValueDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TypeMapperTest {

    @Autowired
    private TypeMapper typeMapper;

    @Test
    public void test() {
        List<NameAndValueDto> allTypeAndCount = typeMapper.getAllTypeAndCount();
        System.out.println(allTypeAndCount);
    }

    @Test
    public void getStatusCountOfComplain(){
        System.out.println(typeMapper.getStatusCountOfComplain());
    }
}
