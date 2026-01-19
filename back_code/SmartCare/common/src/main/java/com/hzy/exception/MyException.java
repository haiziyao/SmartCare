package com.hzy.exception;

import com.hzy.result.ResultCodeEnum;
import lombok.Data;

/**
 * 作者：亥子曜
 * -后之览者，亦将有感于斯文
 */
@Data
public class MyException extends RuntimeException {
    public Integer code;

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public MyException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
