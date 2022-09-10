package cn.edu.zjou.controller;

import cn.edu.zjou.service.WordCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordCloudController {

    private final WordCloudService wordCloudService;

    public WordCloudController(WordCloudService wordCloudService) {
        this.wordCloudService = wordCloudService;
    }

    @GetMapping("/wordcloud/all")
    public String getPicUrlOfAllTypes(String date) {

        return "http://localhost:8082" + wordCloudService.generatePicUrlOfAll(date);
    }

    @GetMapping("/wordcloud/complain")
    public String getPicUrlOfComplain(String date) {

        return "http://localhost:8082" + wordCloudService.generatePicUrlOfComplain(date);
    }
}
