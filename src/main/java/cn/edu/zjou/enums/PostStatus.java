package cn.edu.zjou.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

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

    private static final Map<Integer, PostStatus> map = new HashMap<>();

    static {
        PostStatus[] postStatuses = PostStatus.values();
        for (PostStatus postStatus : postStatuses) {
            map.put(postStatus.getKey(), postStatus);
        }
    }

    public static PostStatus getPostStatusByKey(int key) {
        return map.get(key);
    }
}
