package vn.edu.hcmut.cse.adsoftweng.lab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    private    String id;

    private String name;
    private String email;
    private int age;
}

