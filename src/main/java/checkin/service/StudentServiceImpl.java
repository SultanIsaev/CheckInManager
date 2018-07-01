package checkin.service;

import checkin.dao.StudentDao;
import checkin.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Transactional
    public void addStudent(Student student) {
        this.studentDao.addStudent(student);
    }

    @Transactional
    public void updateStudent(Student student) {
        this.studentDao.updateStudent(student);
    }

    @Transactional
    public void removeStudent(int id) {
        this.studentDao.removeStudent(id);
    }

    @Transactional
    public Student getStudentById(int id) {
        return this.studentDao.getStudentById(id);
    }

    @Transactional
    public List<Student> findAll() {
        return this.studentDao.findAll();
    }
}
