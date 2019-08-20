package orz.joey.service.dto.common;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 5561289059356647754L;
    private int code = 0;
    private String msg = "success";
    private T data;

    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse(CustomError error) {
        this.code = error.getCode();
        this.msg = error.getMsg();
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

//    public static <T> BaseResponse<T> ok(T data) {
//        return new BaseResponse<>(data);
//    }
//
//    public static BaseResponse notOk(CustomError error) {
//        return new BaseResponse(error);
//    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
