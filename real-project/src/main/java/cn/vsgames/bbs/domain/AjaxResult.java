package cn.vsgames.bbs.domain;

/**
 * Ajax请求的返回状态，会转换成json格式返回
 * 
 * @author 吴操
 */
public class AjaxResult {

    /**
     * Ajax请求是否成功：true成功，false失败
     */
    private boolean status;

    /**
     * Ajax请求返回的消息，如失败原因
     */
    private String  message;

    public AjaxResult() {
    }

    public AjaxResult(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public static AjaxResult success(String message) {
        return new AjaxResult(true, message);
    }

    public static AjaxResult failed(String message) {
        return new AjaxResult(false, message);
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
