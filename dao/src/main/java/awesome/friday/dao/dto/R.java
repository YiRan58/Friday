package awesome.friday.dao.dto;

/**
 * @author yiran
 * @date: 20.11.1 14:44
 */
public class R<T> {

    public static final Integer SUCCESS = 0;

    public static final Integer ERROR = 1;

    private Integer code;

    private String errMsg;

    private T data;

    public R() {
    }

    public Integer getCode() {
        return code;
    }

    public R<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public R<T> setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public T getData() {
        return data;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> R<T> ok() {
        return new R<T>().setCode(SUCCESS);
    }

    public static <T> R<T> ok(T data) {
        return new R<T>().setCode(SUCCESS).setData(data);
    }

    public static <T> R<T> errMsg() {
        return new R<T>().setCode(ERROR);
    }

    public static <T> R<T> errMsg(String msg) {
        return new R<T>().setCode(ERROR).setErrMsg(msg);
    }

    public static <T> R<T> errMsg(String msg, T data) {
        return new R<T>()
                .setCode(ERROR)
                .setData(data)
                .setErrMsg(msg);
    }
}
