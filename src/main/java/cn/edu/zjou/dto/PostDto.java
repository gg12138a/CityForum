package cn.edu.zjou.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PostDto {

    @TableId
    private Integer postId;
    private String title;
    private Integer checkCount;
    private Integer replyCount;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date publishDate;

    private String publisherName;

    private String deptName;
    private String typeName;
}
