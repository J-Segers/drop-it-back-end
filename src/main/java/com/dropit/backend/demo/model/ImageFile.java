package com.dropit.backend.demo.model;

import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.*;

@Entity
public class ImageFile {
    @Id
    @GeneratedValue
    Long id;

    public String name, type;

    @Lob
    byte[] data;

    @OneToOne(mappedBy = "imageFile")
    Song song;

    @OneToOne(mappedBy = "imageFile")
    User user;

    public ImageFile(){}

    public ImageFile(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
