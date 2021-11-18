package com.dropit.backend.demo.service;

import com.dropit.backend.demo.exception.RecordNotFoundException;
import com.dropit.backend.demo.model.ImageFile;
import com.dropit.backend.demo.repository.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ImageFileServiceImpl implements ImageFileService{
    private ImageFileRepository imageFileRepository;

    @Autowired
    public ImageFileServiceImpl(ImageFileRepository imageFileRepository) {
        this.imageFileRepository = imageFileRepository;
    }

    @Override
    public Stream<ImageFile> getImages() {
        return imageFileRepository.findAll().stream();
    }

    @Override
    public ImageFile getImage(Long id) {
        Optional optionalImage = imageFileRepository.findById(id);

        if(optionalImage.isEmpty()) {
            throw new RecordNotFoundException("Image could not be found!");
        }
        return (ImageFile) optionalImage.get();
    }

    @Override
    public ImageFile getImageByNameEquals(String name) {
        ImageFile imageName = imageFileRepository.findByNameEquals(name);
        return imageName;
    }

    @Override
    public ImageFile storeImage(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ImageFile imageFile = new ImageFile(fileName, file.getContentType(), file.getBytes());

        return (ImageFile) imageFileRepository.save(imageFile);
    }

    @Override
    public void deleteImage(Long id) {
        imageFileRepository.deleteById(id);
    }
}
