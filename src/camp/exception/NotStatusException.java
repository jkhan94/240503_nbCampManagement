package camp.exception;

public class NotStatusException extends Exception{
    public NotStatusException() {
        super("상태를 입력해주세요!");
    }
}
