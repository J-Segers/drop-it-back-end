package com.dropit.backend.demo.service;

import com.dropit.backend.demo.model.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface ImageFileService {
    Stream<ImageFile> getImages();
    ImageFile getImage(Long id);
    ImageFile getImageByNameEquals(String name);
    ImageFile storeImage(MultipartFile file) throws IOException;
    void deleteImage(Long id);
}
