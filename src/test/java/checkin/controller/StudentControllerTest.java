package checkin.controller;

import checkin.model.Student;
import checkin.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void testAdd() throws Exception{

        Student student = new Student();
        student.setId(0);
        student.setFirstName("Ivan");
        student.setLastName("Ivanov");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = dateFormat.parse("1999/12/31");
        student.setBirthDate(date);
        student.setEmail("Ivan@mail.ru");
        student.setPhoneNumber("8(911)-111-22-33");
        student.setKnowledge("Java");
        student.setEnglishLevel("Intermediate");

        mockMvc.perform(
                post("/students/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(student)))

                .andExpect(status().isFound())
                .andExpect(header().string("/students/add", (String) null))
                .andExpect(redirectedUrl("/students"));
                //Wanted to continue, but could not
                //.andExpect(model().attribute("student", hasProperty("firstName", is("Ivan"))));

    }

    @Test
    public void testFindAll() throws Exception{
        mockMvc.perform(
                get("/students")
        )       .andExpect(status().isOk())
                .andExpect(header().string("/students/", (String) null))
                .andExpect(view().name("studentsList"))
                .andExpect(forwardedUrl("studentsList"))
                .andReturn();
    }

    @Test
    public void testUpdate() throws Exception{
        mockMvc.perform(
                get("/update/" + anyInt())
        )       .andExpect(status().isOk())
                .andExpect(header().string("/update/{id}", (String) null))
                .andExpect(view().name("studentsList"));
    }

    @Test
    public void testRemove() throws Exception{
        mockMvc.perform(
                get("/remove/" + anyInt())
        )       .andExpect(status().isFound())
                .andExpect(header().string("/remove/{id}", (String) null))
                .andExpect(redirectedUrl("/students"));
    }

    @Test
    public void testGetById() throws Exception{
        mockMvc.perform(
                get("/get/" + anyInt())
        )       .andExpect(status().isOk())
                .andExpect(header().string("/get/{id}", (String) null))
                .andExpect(view().name("studentdata"));
    }

    @Test
    public void testLogout() throws Exception{
        mockMvc.perform(
                get("/logout")
        )       .andExpect(status().isFound())
                .andExpect(header().string("spring_security_login", (String) null))
                .andExpect(redirectedUrl("/admin"));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
