package camp.function;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

// 리스트 초기값 설정
// 고유번호 부여
public class InitializeData {
    // 데이터 저장소
    private List<Student> studentStore;
    private List<Subject> subjectStore = new LinkedList<>();
    private List<Score> scoreStore = new LinkedList<>();

    // 과목 타입
    private String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private int studentIndex = 0;
    protected static final String INDEX_TYPE_STUDENT = "ST";
    private int subjectIndex = 0;
    protected static final String INDEX_TYPE_SUBJECT = "SU";
//    private int scoreIndex;
//    protected static final String INDEX_TYPE_SCORE = "SC";


    public List<Student> getStudentStore() {
        return studentStore;
    }

    public List<Subject> getSubjectStore() {
        return subjectStore;
    }

    public List<Score> getScoreStore() {
        return scoreStore;
    }

    public void setStudentStore(Student student) {
        this.studentStore.add(student);
    }

    // 초기 데이터 생성 : 과목 데이터
    public void setInitData() {
        // studentStore = new ArrayList<>();
        // scoreStore = new ArrayList<>();
        studentStore = new LinkedList<>(Stream.of(
                new Student(
                        sequence(INDEX_TYPE_STUDENT),
                        "여단",
                        "Green",
                        new LinkedList<>(List.of("Java", "객체지향", "Spring", "디자인 패턴", "Spring Security"))
                ),
                new Student(
                        sequence(INDEX_TYPE_STUDENT),
                        "이단",
                        "Red",
                        new LinkedList<>(List.of("Java", "객체지향", "Spring", "JPA", "디자인 패턴", "Spring Security", "Redis"))
                ),
                new Student(
                        sequence(INDEX_TYPE_STUDENT),
                        "삼단",
                        "Yellow",
                        new LinkedList<>(List.of("Java", "객체지향", "Spring", "JPA", "MySQL", "디자인 패턴", "Spring Security", "Redis", "MongoDB"))
                )
        ).toList());
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = Stream.of(
                new Score(
                        studentStore.get(0).getStudentId(),
                        subjectStore.get(0).getSubjectId(),
                        1,
                        10,
                        'N'
                ),
                new Score(
                        studentStore.get(1).getStudentId(),
                        subjectStore.get(1).getSubjectId(),
                        1,
                        20,
                        'N'
                ),
                new Score(
                        studentStore.get(2).getStudentId(),
                        subjectStore.get(2).getSubjectId(),
                        1,
                        30,
                        'N'
                )
        ).toList();
    }

    // index 자동 증가 : 고유번호
    // createStudent, setInitData
    public String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                return " ";
            }
        }
    }

//    public String sequence(String type) {
//        switch (type) {
//            case INDEX_TYPE_STUDENT -> {
//                studentIndex++;
//                return INDEX_TYPE_STUDENT + studentIndex;
//            }
//            case INDEX_TYPE_SUBJECT -> {
//                subjectIndex++;
//                return INDEX_TYPE_SUBJECT + subjectIndex;
//            }
//            default -> {
//                scoreIndex++;
//                return INDEX_TYPE_SCORE + scoreIndex;
//            }
//        }
//    }
}

