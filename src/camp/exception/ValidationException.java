package camp.exception;

import java.util.*;

public class ValidationException extends Exception {
    public ValidationException(LinkedList<String> errors) {
        Set<String> set = new LinkedHashSet<>(errors);
        LinkedList<String> distinctErrors = new LinkedList<>(set);

        System.out.print("<발생한 에러 목록>\n");
        Iterator<String> iter = distinctErrors.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}