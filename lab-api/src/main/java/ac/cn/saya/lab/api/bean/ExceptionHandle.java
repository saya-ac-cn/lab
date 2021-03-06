package ac.cn.saya.lab.api.bean;

import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.tools.Log4jUtils;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.api.tools.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class ExceptionHandle {


    private static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return ResultUtil.error(myException.getCode(), myException.getMessage());
        } else {
            //不在定义范围内的异常错误
            logger.error("处理未能捕获的异常" + Log4jUtils.getTrace(e));
            return ResultUtil.error(-1, "未知错误");
        }
    }

}