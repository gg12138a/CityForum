package cn.edu.zjou.dto;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameAndValueDto {
    @TableId
    private String name;
    private Long value;
}
