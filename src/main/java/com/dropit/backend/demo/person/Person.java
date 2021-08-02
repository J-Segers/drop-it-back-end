package com.dropit.backend.demo.person;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Person {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private String country;
    private boolean isArtist = false;
    private String stageName;
    private String genre;
    @Transient
    private int age;
    private LocalDate joinDate;

    public Person() {
    }

    public Person(Long id, LocalDate joinDate, String name, LocalDate dob, String email, String country, boolean isArtist, String stageName, String genre) {
        this.id = id;
        this.joinDate = joinDate;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.country = country;
        this.isArtist = isArtist;
        this.stageName = stageName;
        this.genre = genre;
    }

    public Person(LocalDate joinDate, String name, LocalDate dob, String email, String country, boolean isArtist, String stageName, String genre) {
        this.joinDate = joinDate;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.country = country;
        this.isArtist = isArtist;
        this.stageName = stageName;
        this.genre = genre;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getJoinDate() { return joinDate; }

    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {return country;}

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsArtist() { return  isArtist;}

    public void setIsArtist() { this.isArtist = !isArtist;}

    public String getStageName() { return stageName; }

    public void setStageName(String stageName){
        this.stageName = stageName;
    }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public String  toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
