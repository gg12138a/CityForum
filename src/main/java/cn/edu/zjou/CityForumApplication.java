package cn.edu.zjou;

import cn.edu.zjou.utils.PythonSupport;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("cn.edu.zjou.mapper")
public class CityForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityForumApplication.class);
    }
}
