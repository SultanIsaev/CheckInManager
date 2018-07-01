package checkin.controller;

import checkin.model.Student;
import checkin.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StudentController {
    final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private StudentService studentService;

    @Autowired
    @Qualifier(value = "studentService")
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = {"/", "/welcome**" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("student", new Student());

        return "hello";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        logger.info("User successfully logout");
        return "redirect:/admin";
    }

    @RequestMapping(value = {"/admin**"}, method = RequestMethod.GET)
    public String adminPage(Model model, HttpServletRequest request) {
        model.addAttribute("student", new Student());
        model.addAttribute("listStudents", this.studentService.findAll());

        return "redirect:/students";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ModelAndView findAll(){
        ModelAndView model = new ModelAndView();
        model.addObject("student", new Student());
        model.addObject("listStudents", this.studentService.findAll());
        //?
        model.setViewName("studentsList");
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("student") Student student){
        if(student.getId() == 0){
            this.studentService.addStudent(student);
        }else{
            this.studentService.updateStudent(student);
        }
        return "congrats";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/students/add", method = RequestMethod.POST)
    public ModelAndView addStudent(@ModelAttribute("student") Student student){
        ModelAndView model = new ModelAndView();
        if(student.getId() == 0){
            this.studentService.addStudent(student);
        }else {
            this.studentService.updateStudent(student);
        }

        model.addObject("student", student);
        model.setViewName("redirect:/students");
        return model;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "update/{id}")
    public String updateStudent(@ModelAttribute("id") int id, Model model){
        model.addAttribute("student", this.studentService.getStudentById(id));
        model.addAttribute("listStudents", this.studentService.findAll());

        return "studentsList";
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/remove/{id}")
    public String removeStudent(@PathVariable("id") int id){

        this.studentService.removeStudent(id);

        return "redirect:/students";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/get/{id}")
    public ModelAndView getStudentById(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView();
        model.addObject("student", this.studentService.getStudentById(id));
        model.setViewName("studentdata");

        return model;
    }
}