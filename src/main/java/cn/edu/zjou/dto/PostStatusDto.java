package cn.edu.zjou.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class PostStatusDto {

    @TableId
    private Integer key;
    private Long count;
    private String statusName;
}
