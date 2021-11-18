package com.dropit.backend.demo.repository;

import com.dropit.backend.demo.model.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository {
    ImageFile findByNameEquals(String Name);
}
