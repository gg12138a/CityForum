package cn.edu.zjou.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@Getter
public class StatusIdAndCountDto {

    @TableId
    private Long statusId;
    private Long value;
}
