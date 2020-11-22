package cn.kirbyhao.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一错误码
 *
 * @author Nestist KirbyHao
 * Created At 2020-11-22
 * @see APIResponse
 */
@AllArgsConstructor
@Getter
public enum ErrorCode {

    /**
     * 请求成功
     */
    OK(0, "请求成功"),

    /*--------------------------------------------------------------*/
    /*------------------------- 用户权限方面 -------------------------*/
    /*--------------------------------------------------------------*/
    /**
     * 登录失效，包括令牌过期以及令牌错误
     */
    AUTH_INVALID(-1000, "用户登录令牌失效，请重新登录"),
    /**
     * 权限不足
     */
    AUTH_NO_PERMISSION(-1001, "用户权限不足"),

    /*-------------------------------------------------------------*/
    /*------------------------ 参数问题方面 -------------------------*/
    /*-------------------------------------------------------------*/
    /**
     * 需要的参数为空
     */
    PARAM_REQUEST_NOT_NULL(-2000, "必要的参数不能为空"),
    /**
     * 参数类型错误
     */
    PARAM_TYPE_ERROR(-2001, "参数类型错误"),
    /**
     * 参数长度过长
     */
    PARAM_OVER_LENGTH(-2002, "参数长度过长"),

    /*-------------------------------------------------------------*/
    /*------------------------- 数据库方面 --------------------------*/
    /*-------------------------------------------------------------*/
    /**
     * 连接数据库超时
     */
    DATABASE_TIMEOUT(-3000, "连接数据库超时"),
    /**
     * 插入数据库出错
     */
    DATABASE_INSERT_ERROR(-3001, "插入数据库出错"),
    /**
     * 更新数据库出错
     */
    DATABASE_UPDATE_ERROR(-3002, "更新数据库出错"),
    /**
     * 删除数据库出错
     */
    DATABASE_DELETE_ERROR(-3003, "删除数据库出错"),
    /**
     * 软删除数据库出错
     */
    DATABASE_SOFT_DELETE_ERROR(-3004, "软删除数据库出错"),
    /**
     * 硬删除数据库出错
     */
    DATABASE_HARD_DELETE_ERROR(-3005, "硬删除数据库出错"),
    /**
     * 数据库语法错误
     */
    DATABASE_SYNTAX_ERROR(-3006, "数据库语法错误"),

    /*--------------------------------------------------------------*/
    /*------------------------- 业务逻辑方面 -------------------------*/
    /*--------------------------------------------------------------*/
    /**
     * 内部逻辑错误
     */
    LOGIC_ERROR(-4000, "内部逻辑错误"),
    /**
     * 空指针错误
     */
    LOGIC_NULL_POINTER(-4001, "空指针错误"),
    /**
     * 类型强制转换错误
     */
    LOGIC_CLASSIFICATION_CAST_ERROR(-4002, "类型强制转换错误");

    /**
     * 错误码
     */
    Integer code;
    /**
     * 错误信息简述
     */
    String message;

    /**
     * 通过code获取message
     *
     * @param code 错误码值
     * @return 错误信息简述
     */
    public String getMessageByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (code.equals(errorCode.getCode())) {
                return errorCode.getMessage();
            }
        }
        return null;
    }

    /**
     * 通过message获取code
     *
     * @param message 错误信息简述
     * @return 错误码值，找不到会返回-10000
     */
    public Integer getCodeByMessage(String message) {
        if (message == null) {
            return null;
        }
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (message.equals(errorCode.getMessage())) {
                return errorCode.getCode();
            }
        }
        return null;
    }
}
