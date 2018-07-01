package checkin.dao;

import checkin.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    private static final Logger logger = LoggerFactory.getLogger(Student.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void addStudent(Student student) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(student);
        logger.info("Student successfully saved. Student details: " + student);
    }

    public void updateStudent(Student student) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(student);
        logger.info("Student successfully updated. Student details: " + student);
    }

    public void removeStudent(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Student student = (Student) session.load(Student.class, new Integer(id));

        if(student != null){
            session.delete(student);
        }
        logger.info("Student successfully removed. Student details: " + student);
    }

    public Student getStudentById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Student student = (Student) session.load(Student.class, new Integer(id));
        logger.info("Student successfully loaded. Student details: " + student);

        return student;
    }

    public List<Student> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Student> studentList = session.createQuery("from Student").list();

        for(Student student : studentList){
            logger.info("Student list: " + student);
        }
        return studentList;
    }
}
