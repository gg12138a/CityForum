package cn.edu.zjou.utils;


import jep.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://blog.csdn.net/hetongun/article/details/82155032">...</a>
 * <a href="https://www.cnblogs.com/nuccch/p/8435693.html">...</a>
 * <a href="https://www.anycodings.com/1questions/2867198/pass-data-from-java-to-python-and-back-jython">...</a>
 */
public class PythonSupport {

    private static final Logger logger = LoggerFactory.getLogger(PythonSupport.class);
    private static final String PySCRIPT_PATH;
    private static final String STOPWORDS_PATH;
    private static final String PICTURE_SAVE_PATH;


    static {
        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            PySCRIPT_PATH = path.substring(1);
            STOPWORDS_PATH = path.substring(1) + "stopwords.txt";
            PICTURE_SAVE_PATH = path.substring(1) + "static/pictures/";


//            PySCRIPT_PATH = "F:\\gitee_projects\\CityForum\\src\\main\\resources";
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        JepConfig jepConfig = new JepConfig();
        jepConfig.addIncludePaths(PySCRIPT_PATH);
        jepConfig.redirectStdout(System.out);
        jepConfig.redirectStdErr(System.err);
        try {
            SharedInterpreter.setConfig(jepConfig);
        } catch (JepException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runScript(List<String> contentList, String picName) {
        try (Interpreter interp = new SharedInterpreter()) {

            interp.eval("import wordCloudGen");

            interp.exec("gen = wordCloudGen.gen_wordcloud_pic");
            interp.invoke("gen", contentList, picName, STOPWORDS_PATH, PICTURE_SAVE_PATH);
        } catch (JepException e) {
            throw new RuntimeException(e);
        }
    }
}
