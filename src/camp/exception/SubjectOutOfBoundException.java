package camp.exception;

// 존재하지 않는 과목을 입력했을 때 발생하는 예외
public class SubjectOutOfBoundException extends Exception{
    public SubjectOutOfBoundException() {
        super("없는 과목입니다!");
    }
}
