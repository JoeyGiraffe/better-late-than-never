package orz.joey.service.dto.common;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private static final long serialVersionUID = 5561289059356647754L;
    private int code = 0;
    private String msg = "success";
    private T data;

    public Response(T data) {
        this.data = data;
    }

    public Response(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
