package cn.edu.zjou.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WordCloudServiceTest {

    @Autowired
    private WordCloudService wordCloudService;

    @Test
    public void generatePicUrlOfAll(){
        wordCloudService.generatePicUrlOfAll("2022-02");
    }

}
