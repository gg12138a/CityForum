package cn.edu.zjou.dto;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TypeAndCountDto {
    @TableId
    private String name;
    private Long value;
}
