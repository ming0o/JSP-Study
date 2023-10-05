package tukorea.websvc.club.persistence;

import java.util.HashMap;
import java.util.Map;
import tukorea.websvc.club.domain.StudentVO;

public class StudentDAO {
    // Memory Database
    private static Map<String, StudentVO> storage = new HashMap<String, StudentVO>();

    public boolean join(StudentVO student) {
        try {
            storage.put(student.getId(), student);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
