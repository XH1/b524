package com.socog.website.module.person.entiy;

import com.socog.website.module.BaseEntity;
import com.socog.website.module.paper.entity.Paper;

import javax.persistence.*;
import java.util.List;

/**
 * @author xinghao
 * @descreption 人员实体类
 * @date 2018/12/18
 */

@Entity
public class Person extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(length = 20)
    private String telephone;

    @Column(length = 64)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Degree degree;

    private String unit;

    private String field;

    private int grade;

    private String photo;

    private String brief;

    private boolean graduated;

    @ManyToMany
    private List<Paper> paperList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<Paper> getPaperList() {
        return paperList;
    }

    public void setPaperList(List<Paper> paperList) {
        this.paperList = paperList;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }
}
