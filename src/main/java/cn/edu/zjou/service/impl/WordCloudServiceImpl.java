package cn.edu.zjou.service.impl;

import cn.edu.zjou.mapper.MsgMapper;
import cn.edu.zjou.po.Msg;
import cn.edu.zjou.service.WordCloudService;
import cn.edu.zjou.utils.PythonSupport;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class WordCloudServiceImpl extends ServiceImpl<MsgMapper, Msg>
        implements WordCloudService {

    private final Logger logger = LoggerFactory.getLogger(WordCloudServiceImpl.class);
    private final MsgMapper msgMapper;
    private final Calendar calendar = Calendar.getInstance();
    private final String PIC_SAVE_PATH_FROM_RESOURCES = "/pictures/";

    private final String PRE_ALL = "all";

//    {
//        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//    }

    public WordCloudServiceImpl(MsgMapper msgMapper) {
        this.msgMapper = msgMapper;
    }


    public String generatePicUrlOfAll(String month) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            String nextMonth = getNextMonth(month);
            List<String> allContent = msgMapper.getAllContent(format.parse(month), format.parse(nextMonth));

            return generatePic(allContent, PRE_ALL, month);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    private String generatePic(List<String> contents, String pre, String month) {
        String fileName = pre + month + ".jpg";
        PythonSupport.runScript(contents, fileName);
        return PIC_SAVE_PATH_FROM_RESOURCES + fileName;
    }

    private String getNextMonth(String currentMonth) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        try {
            Date date = format.parse(currentMonth);
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, 1);
            String nextMonth = format.format(calendar.getTime());
            logger.debug("第一天：{}，最后一天：{}", currentMonth, nextMonth);

            return nextMonth;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
