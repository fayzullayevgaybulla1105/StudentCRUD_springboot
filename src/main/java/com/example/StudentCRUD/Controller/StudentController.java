package com.example.StudentCRUD.Controller;

import com.example.StudentCRUD.Domain.Student;
import com.example.StudentCRUD.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/")
    public String viewHomePage(Model model){
        List<Student> listStudent = service.listAll();
        model.addAttribute("listStudent", listStudent);
        System.out.print("GET/ ");
        return "index";
    }

    @GetMapping("/new")
    public String add (Model model){
        model.addAttribute("student", new Student());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student std){
        service.save(std);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditStudentPage(@PathVariable(name="id") int id){
        ModelAndView mav = new ModelAndView("new");
        Student std = service.get(id);
        mav.addObject("student",std);
        return  mav;
    }

    @RequestMapping ("/delete/{id}")
    public String deleteStudent (@PathVariable(name="id")int id){
        service.delete(id);
        return "redirect:/";
    }


}
