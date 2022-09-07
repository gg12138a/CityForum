package cn.edu.zjou.service;

import cn.edu.zjou.po.Msg;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WordCloudService extends IService<Msg> {

    public String generatePicUrlOfAll(String month);
}
