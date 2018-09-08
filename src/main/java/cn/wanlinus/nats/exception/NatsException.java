package cn.wanlinus.nats.exception;

/**
 * 注解出问题报错
 *
 * @author wanli
 * @date 2018-09-08
 */
public class NatsException extends RuntimeException {

    private String message;

    public NatsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
