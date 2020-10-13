package com.example.producuploadfile.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cId;

    private String cName;

//    public Category() {
//    }
//
//    public Category(String cName) {
//        this.cName = cName;
//    }
//
//    public Category(Long cId, String cName) {
//        this.cId = cId;
//        this.cName = cName;
//    }
//
//    public Long getcId() {
//        return cId;
//    }
//
//    public void setcId(Long cId) {
//        this.cId = cId;
//    }
//
//    public String getcName() {
//        return cName;
//    }
//
//    public void setcName(String cName) {
//        this.cName = cName;
//    }
}


