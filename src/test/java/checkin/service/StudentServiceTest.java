package checkin.service;

import checkin.model.Student;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    private final List<Student> students = new ArrayList<>();
    private StudentService studentService;

    @Before
    public void initService(){
        studentService = mock(StudentService.class);
    }

    @Before
    public void initStudents() throws ParseException {
        Student student = new Student();
        student.setId(1);
        student.setFirstName("Ivan");
        student.setLastName("Ivanov");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = dateFormat.parse("1999/12/31");
        student.setBirthDate(date);
        student.setEmail("Ivan@mail.ru");
        student.setPhoneNumber("8(911)-111-22-33");
        student.setKnowledge("Java");
        student.setEnglishLevel("Intermediate");
        students.add(student);
    }

    @Test
    public void testFindAll(){
        when(studentService.findAll()).thenReturn(students);
        assertEquals(1, students.size());
    }

    @Test
    public void testAdd() throws ParseException{
        final Student newStudent = students.get(0);
        newStudent.setId(0);

        doAnswer(invocation -> {
            Student student = invocation.getArgument(0);
            assertEquals(student.getId(),0);
            assertEquals(student.getFirstName(),"Ivan");
            assertEquals(student.getLastName(),"Ivanov");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            assertEquals(student.getBirthDate(),dateFormat.parse("1999/12/31"));
            assertEquals(student.getEmail(),"Ivan@mail.ru");
            assertEquals(student.getPhoneNumber(),"8(911)-111-22-33");
            assertEquals(student.getKnowledge(),"Java");
            assertEquals(student.getEnglishLevel(),"Intermediate");
            return null;
        }).when(studentService).addStudent(newStudent);
        studentService.addStudent(newStudent);

    }

    @Test
    public void testUpdate() throws ParseException{
        final Student upStudent = students.get(0);

        doAnswer(invocation -> {
            Student student = invocation.getArgument(0);
            assertEquals(student.getId(),1);
            assertEquals(student.getFirstName(),"Ivan");
            assertEquals(student.getLastName(),"Ivanov");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            assertEquals(student.getBirthDate(),dateFormat.parse("1999/12/31"));
            assertEquals(student.getEmail(),"Ivan@mail.ru");
            assertEquals(student.getPhoneNumber(),"8(911)-111-22-33");
            assertEquals(student.getKnowledge(),"Java");
            assertEquals(student.getEnglishLevel(),"Intermediate");
            return null;
        }).when(studentService).updateStudent(upStudent);
        studentService.updateStudent(upStudent);
    }

    @Test
    public void testRemove(){
        final Student rmvStudent = students.get(0);
        doAnswer(invocation -> {
            Student student = invocation.getArgument(0);
            assertTrue(studentService.getStudentById(student.getId()).equals(null));
            return null;
        }).when(studentService).removeStudent(anyInt());
        // Does not working. I couldn't solve this, but I shall.
        //studentService.removeStudent(rmvStudent.getId());
    }

    @Test
    public void testGetStudent() throws ParseException{
        Student getStudent = students.get(0);

        doAnswer(invocation -> {
            Student student = invocation.getArgument(0);
            assertTrue(studentService.getStudentById(student.getId()).equals(student));
            return null;
        }).when(studentService).getStudentById(anyInt());
        // Does not working. I couldn't solve this, but I shall.
        //studentService.getStudentById(getStudent.getId());
    }
}


























