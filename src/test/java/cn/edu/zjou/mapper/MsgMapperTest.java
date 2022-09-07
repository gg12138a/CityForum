package cn.edu.zjou.mapper;

import cn.edu.zjou.po.Msg;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class MsgMapperTest {

    @Autowired
    private MsgMapper mapper;

    @Test
    public void getAllContent() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

        List<String> allContent = mapper.getAllContent(format.parse("2022-02"), format.parse("2022-03"));

        System.out.println(allContent.size());
        System.out.println(allContent.get(0));
        System.out.println(allContent.get(100));
        System.out.println(allContent.get(250));
        System.out.println(allContent.get(500));
    }
}
