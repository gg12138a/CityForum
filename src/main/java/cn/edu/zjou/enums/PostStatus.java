package cn.edu.zjou.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author G_xy
 */

@Getter
public enum PostStatus {
    /**
     * 未设置状态
     */
    UNKNOWN(0, "处理中"),
    /**
     * 已回复
     */
    REPLIED(1, "已回复"),
    /**
     * 已受理
     */
    ACCEPTED(2, "已受理"),
    /**
     * 不受理
     */
    REJECTED(3, "不受理");

    @EnumValue
    private final int key;

    private final String statusName;

    PostStatus(int key, String statusName) {
        this.key = key;
        this.statusName = statusName;
    }
}
