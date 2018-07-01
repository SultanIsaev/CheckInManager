package checkin.service;

import checkin.model.Student;

import java.util.List;

public interface StudentService {
    public void addStudent(Student student);
    public void updateStudent(Student student);
    public void removeStudent(int id);
    public Student getStudentById(int id);
    public List<Student> findAll();
}
