package cn.edu.zjou.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVo<T> {

    private String code;
    private String message;
    private T data;

    public static ResponseVo returnOk(Object data) {
        return new ResponseVo("200", "接口调用成功", data);
    }

    public static ResponseVo<Object> returnError(String code, String message) {
        return new ResponseVo<>(code, message, null);
    }

    public static ResponseVo<Object> returnError(String message) {
        return new ResponseVo<>("-1", message, null);
    }
}
