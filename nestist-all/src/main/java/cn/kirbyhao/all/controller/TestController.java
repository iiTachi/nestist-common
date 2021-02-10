package cn.kirbyhao.all.controller;

import cn.kirbyhao.core.web.response.ApiResponse;
import cn.kirbyhao.core.web.response.ErrorCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lu Hao
 * @date 2021-02-08
 */
@RestController
public class TestController {

    @RequestMapping("/test/success")
    public ApiResponse<?> testSuccess() {
        return ApiResponse.success();
    }

    @RequestMapping("/test/success/withResult")
    public ApiResponse<?> testSuccessWithResult() {
        return ApiResponse.success("result");
    }

    @RequestMapping("/test/success/withResult/withAddition")
    public ApiResponse<?> testSuccessWithResultWithAddition() {
        return ApiResponse.success("result", "additionMsg");
    }

    @RequestMapping("/test/error/withErrorCode")
    public ApiResponse<?> testErrorWithResultWithErrorCode() {
        return ApiResponse.error(ErrorCode.AUTH_INVALID);
    }

    @RequestMapping("/test/error/withErrorCode/withResult")
    public ApiResponse<?> testErrorWithErrorCodeWithResult() {
        return ApiResponse.error(ErrorCode.AUTH_INVALID, "result");
    }

    @RequestMapping("/test/error/withErrorCode/withResult/withAddition")
    public ApiResponse<?> testErrorWithErrorCodeWithResultWithAddition() {
        return ApiResponse.error(ErrorCode.AUTH_INVALID, "result", "additionMsg");
    }

    @RequestMapping("/test/error/custom/withResult")
    public ApiResponse<?> testErrorCustomWithResult() {
        return ApiResponse.error(500, "伍佰", "result");
    }

    @RequestMapping("/test/error/custom/withResult/withAddition")
    public ApiResponse<?> testErrorCustomWithResultWithAddition() {
        return ApiResponse.error(500, "伍佰", "result", "additionMsg");
    }
}

