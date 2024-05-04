package camp.exception;

// 입력한 과목 수가 부족할 떄 방생
public class NotEnoughSubjectsException extends Exception {
    public NotEnoughSubjectsException() {
        super("필수과목 3개 이상, 선택과목 2개 이상 입력해야 합니다!");
    }
}
