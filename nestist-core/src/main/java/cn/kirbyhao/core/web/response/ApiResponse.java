package cn.kirbyhao.core.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回模型
 *
 * @author Lu Hao
 * @date 2020-11-22
 */
@Data
@NoArgsConstructor
public class ApiResponse<T> {

    /**
     * 错误码
     *
     * @see ErrorCode
     */
    @JsonProperty("errorCode")
    private Integer errorCode;

    /**
     * 错误信息
     *
     * @see ErrorCode
     */
    @JsonProperty("message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * 数据结果
     */
    @JsonProperty("result")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    /**
     * 附加内容
     */
    @JsonProperty("additionMsg")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object additionMsg;

    /* **** 构造方法 **** */

    public ApiResponse(Integer errorCode) {
        this.errorCode = errorCode;
        this.message = null;
        this.result = null;
        this.additionMsg = null;
    }

    public ApiResponse(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.result = null;
        this.additionMsg = null;
    }

    public ApiResponse(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.result = null;
        this.additionMsg = null;
    }

    public ApiResponse(Integer errorCode, String message, T result) {
        this.errorCode = errorCode;
        this.message = message;
        this.result = result;
        this.additionMsg = null;
    }

    public ApiResponse(ErrorCode errorCode, T result) {
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.result = result;
        this.additionMsg = null;
    }

    public ApiResponse(Integer errorCode, String message, T result, Object additionMsg) {
        this.errorCode = errorCode;
        this.message = message;
        this.result = result;
        this.additionMsg = additionMsg;
    }

    public ApiResponse(ErrorCode errorCode, T result, Object additionMsg) {
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.result = result;
        this.additionMsg = additionMsg;
    }

    /**
     * 成功，不返回result值
     * <p>
     * 使用方式: <code>return ApiResponse.success()</code>
     * <p>
     * 将会得到: <code>{"errorCode": 0, "message": "请求成功"}</code>的响应
     *
     * @return 统一返回模型
     */
    public static ApiResponse<?> success() {
        return new ApiResponse<>(ErrorCode.OK);
    }

    /**
     * 成功，且有返回值
     * <p>
     * 使用方式: e.g.
     * <code>return ApiResponse.success(student)</code>
     * <p>
     *
     * @param result 返回值数据
     *
     * @return 统一返回模型
     */
    public static ApiResponse<?> success(Object result) {
        return new ApiResponse<>(ErrorCode.OK, result);
    }

    /**
     * 成功，有返回值以及附加内容
     *
     * @param result 返回值数据
     *
     * @return 统一返回模型
     */
    public static ApiResponse<?> success(Object result, Object additionMsg) {
        return new ApiResponse<>(ErrorCode.OK, result, additionMsg);
    }

    /**
     * 失败，无附加信息
     *
     * @param errorCode 错误吗枚举
     *
     * @return 统一返回模型
     *
     * @see ErrorCode
     */
    public static ApiResponse<?> error(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode);
    }

    /**
     * 失败，返回具体的错误消息；或返回附加内容
     * 将"具体错误消息"或"附加的返回内容"放在additionMsg中
     *
     * @param errorCode 错误码枚举
     * @param result 结果
     *
     * @return 统一返回模型
     *
     * @see ErrorCode
     */
    public static ApiResponse<?> error(ErrorCode errorCode, Object result) {
        return new ApiResponse<>(errorCode, result);
    }

    /**
     * 失败，返回具体的错误消息；或返回附加内容
     * 将"具体错误消息"或"附加的返回内容"放在additionMsg中
     *
     * @param errorCode 错误码枚举
     * @param result 结果
     *
     * @return 统一返回模型
     *
     * @see ErrorCode
     */
    public static ApiResponse<?> error(ErrorCode errorCode, Object result, Object additionMsg) {
        return new ApiResponse<>(errorCode, result, additionMsg);
    }

    /**
     * 失败，允许自定义错误码、错误消息，以及返回值
     *
     * @param code 自定义的错误码
     * @param msg 自定义消息内容
     *
     * @return 统一返回模型
     */
    public static ApiResponse<?> error(int code, String msg, Object result) {
        return new ApiResponse<>(code, msg, result);
    }

    /**
     * 失败，允许自定义错误码、错误消息，以及返回值
     *
     * @param code 自定义的错误码
     * @param msg 自定义消息内容
     *
     * @return 统一返回模型
     */
    public static ApiResponse<?> error(int code, String msg, Object result, Object additionMsg) {
        return new ApiResponse<>(code, msg, result, additionMsg);
    }
}
