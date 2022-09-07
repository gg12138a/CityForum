package cn.edu.zjou.util;

import cn.edu.zjou.mapper.MsgMapper;
import cn.edu.zjou.utils.PythonSupport;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
public class PythonSupportTest {


    @Autowired
    private MsgMapper msgMapper;

    @Test
    public void test() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

        List<String> allContent = msgMapper.getAllContent(format.parse("2022-02"), format.parse("2022-03"));
        PythonSupport.runScript(allContent,"2022-02.jpg");
    }
}
