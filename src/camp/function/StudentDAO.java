package camp.function;

import camp.CampManagementApplication;
import camp.exception.NotEnoughSubjectsException;
import camp.exception.NotStatusException;
import camp.exception.SubjectOutOfBoundException;
import camp.exception.ValidationException;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

public class StudentDAO {
    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    InitializeData initializeData = new InitializeData();
    // 고유 번호

    // 수강생 등록
    public void createStudent() {
        String studentID = CampManagementApplication.initializeData.sequence(CampManagementApplication.initializeData.INDEX_TYPE_STUDENT);
        String studentName = " ";
        String studentStatus = " ";
        LinkedList<String> statusTypes = new LinkedList<>(List.of("green", "yellow", "red"));
        LinkedList<String> studentSubjects = new LinkedList<>();
        LinkedList<String> mandatorySubjects = new LinkedList<>(); // 최소 3개 이상
        LinkedList<String> choiceSubjects = new LinkedList<>(); // 최소 2개 이상
        int countMandatory = 0;
        int countChoice = 0;
        LinkedList<String> errors = new LinkedList<>();
        boolean printStatus = true;
        boolean printSubject = true;
        String input = " ";
        int index = 0;
        sc = new Scanner(System.in);

        System.out.println(CampManagementApplication.initializeData.getStudentStore());

        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        studentName = sc.nextLine();

        while (true) {
            try {
                if (printStatus) {
                    System.out.print("\n수강생 상태: ");
                    for (int i = 0; i < statusTypes.size(); i++) {
                        System.out.print((i + 1) + "." + statusTypes.get(i) + " ");
                    }
                    System.out.println();
                    System.out.print("수강생 상태를 번호로 입력하세요: ");
                    input = sc.nextLine();
                    index = Integer.parseInt(input);
                    if (index <= 0 || index > statusTypes.size()) {
                        throw new NotStatusException();
                    } else {
                        studentStatus = statusTypes.get(index - 1);
                        printStatus = false;
                    }
                }

                if (printSubject) {
                    countMandatory = 0;
                    countChoice = 0;
                    mandatorySubjects.clear();
                    choiceSubjects.clear();

                    for (int i = 0; i < CampManagementApplication.initializeData.getSubjectStore().size(); i++) {
                        if (CampManagementApplication.initializeData.getSubjectStore().get(i).getSubjectType().equals("MANDATORY")) {
                            mandatorySubjects.add(CampManagementApplication.initializeData.getSubjectStore().get(i).getSubjectName());
                        } else {
                            choiceSubjects.add(CampManagementApplication.initializeData.getSubjectStore().get(i).getSubjectName());
                        }
                    }
                    System.out.print("\n필수과목: ");
                    for (int i = 0; i < mandatorySubjects.size(); i++) {
                        System.out.print((i + 1) + "." + mandatorySubjects.get(i) + " ");
                    }
                    System.out.println();
                    System.out.print("선택과목: ");
                    for (int i = 0; i < choiceSubjects.size(); i++) {
                        System.out.print((i + mandatorySubjects.size() + 1) + "." + choiceSubjects.get(i) + " ");
                    }
                    System.out.println();
                    System.out.println("입력이 끝나면 end 를 입력하세요!");
                    System.out.print("수강생이 선택한 과목 번호를 입력하세요: ");
                }
                if (!errors.isEmpty() && input.equals("end")) {
                    throw new ValidationException(errors);
                }

                input = sc.next();

                if (input.equals("end")) {
                    if (countMandatory >= 3 && countChoice >= 2) {
                        if (!errors.isEmpty()) {
                            throw new ValidationException(errors);
                        }
                        break;
                    } else {
                        throw new NotEnoughSubjectsException();
                    }
                }

                index = Integer.parseInt(input);
                if (index <= 0 || index > (mandatorySubjects.size() + choiceSubjects.size())) {
                    throw new SubjectOutOfBoundException();
                } else if (index <= mandatorySubjects.size()) {
                    studentSubjects.add(mandatorySubjects.get(index - 1));
                    countMandatory++;
                    printSubject = false;
                } else {
                    studentSubjects.add(choiceSubjects.get(index - mandatorySubjects.size() - 1));
                    countChoice++;
                    printSubject = false;
                }
            } catch (NotStatusException e) {
                System.out.println(e.getMessage());
                printStatus = true;
            } catch (NumberFormatException e) {
                System.out.println("번호를 입력하세요!");
                sc = new Scanner(System.in);
                studentSubjects.clear();
            } catch (ValidationException e) {
                errors.clear();
                studentSubjects.clear();
                sc = new Scanner(System.in);
                printSubject = true;
            } catch (SubjectOutOfBoundException e) {
                errors.add(new SubjectOutOfBoundException().getMessage());
                studentSubjects.clear();
                printSubject = false;
            } catch (NotEnoughSubjectsException e) {
                errors.add(new NotEnoughSubjectsException().getMessage());
                sc = new Scanner(System.in);
                studentSubjects.clear();
                printSubject = false;
            }
        }

        Set<String> set = new LinkedHashSet<>(studentSubjects);
        LinkedList<String> distinctStudentSubjects = new LinkedList<>(set);

        Student student = new Student(studentID, studentName, studentStatus, distinctStudentSubjects);
        CampManagementApplication.initializeData.setStudentStore(student);

        System.out.println("수강생 등록 성공!\n");
        System.out.println(CampManagementApplication.initializeData.getStudentStore());
    }

    // 수강생 목록 조회
    public void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        System.out.println("\n수강생 목록 조회 성공!");
    }

}



