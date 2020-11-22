package cn.kirbyhao.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回模型
 *
 * @author Nestist KirbyHao
 * Created At 2020-11-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {

    /**
     * 错误码
     *
     * @see ErrorCode
     */
    private Integer error_code;

    /**
     * 错误信息
     *
     * @see ErrorCode
     */
    private String message;

    /**
     * 数据结果
     */
    private T result;

    public APIResponse(int error_code, String message) {
        this.error_code = error_code;
        this.message = message;
        this.result = null;
    }

    /**
     * 成功，不返回result值
     *
     * @return 统一返回模型
     */
    public static APIResponse<?> success() {
        return APIResponse.result(ErrorCode.OK, null);
    }

    /**
     * 成功，且有返回值
     *
     * @param result 返回值数据
     * @return 统一返回模型
     */
    public static APIResponse<?> success(Object result) {
        return APIResponse.result(ErrorCode.OK, result);
    }

    /**
     * 失败，返回具体的错误消息；或返回附加内容
     * 将"具体错误消息"或"附加的返回内容"放在additionMsg中
     *
     * @param errorCode   错误码枚举
     * @param additionMsg 附加信息
     * @return 统一返回模型
     * @see ErrorCode
     */
    public static APIResponse<?> error(ErrorCode errorCode, Object additionMsg) {
        return APIResponse.result(errorCode, additionMsg);
    }

    /**
     * 失败，无附加信息
     *
     * @param errorCode 错误吗枚举
     * @return 统一返回模型
     * @see ErrorCode
     */
    public static APIResponse<?> error(ErrorCode errorCode) {
        return new APIResponse<>(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * 失败，允许自定义错误码、错误消息，以及附加的内容
     *
     * @param code 自定义的错误码
     * @param msg  自定义消息内容
     * @return 统一返回模型
     */
    public static APIResponse<?> error(int code, String msg, Object additionMsg) {
        return new APIResponse<>(code, msg, additionMsg);
    }

    /**
     * 私有的结果构造器
     *
     * @param errorCode 错误码枚举
     * @param result    填充到result字段的数据
     * @return 统一返回模型
     */
    private static APIResponse<?> result(ErrorCode errorCode, Object result) {
        return new APIResponse<>(errorCode.code, errorCode.getMessage(), result);
    }
}
