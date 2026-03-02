package vn.edu.hcmut.cse.adsoftweng.lab.service;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public void save(Student student) {
        if (student.getId() == null || student.getId().isEmpty()) {
            student.setId(generateId());
        }
        repository.save(student);
    }
    
    public String generateId() {
        Long count = repository.count() + 1;
        return String.valueOf(count);
    }
}