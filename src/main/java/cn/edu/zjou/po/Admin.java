package cn.edu.zjou.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Admin {

    @TableId
    private Integer uid;
    private String username;
    private String password;
    private Integer deptId;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
