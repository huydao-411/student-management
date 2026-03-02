package vn.edu.hcmut.cse.adsoftweng.lab.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import java.util.List;
import java.util.Comparator;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;
    
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {

        List<Student> students;

        if (keyword != null && !keyword.isEmpty()) {
            students = service.searchByName(keyword);
            students.sort(Comparator.comparing(s -> Long.valueOf(s.getId()))); // sort tại đây
        } else {
            students = service.getAll();
            students.sort(Comparator.comparing(s -> Long.valueOf(s.getId()))); // sort tại đây
        }

        model.addAttribute("dsSinhVien", students);
        return "students";
    }

    @GetMapping("/{id}")
    public String viewDetail(@PathVariable String id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "detail";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "create";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = service.getById(id); // lấy dữ liệu cũ
        model.addAttribute("student", student);
        return "create"; // dùng lại form create
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/students";
    }
}
