package com.dropit.backend.demo.controller;

import com.dropit.backend.demo.model.ImageFile;
import com.dropit.backend.demo.responseFile.ResponseFile;
import com.dropit.backend.demo.responseFile.ResponseMessage;
import com.dropit.backend.demo.service.ImageFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("/images")
public class ImageFileController {
    private final ImageFileService imageFileService;

    @Autowired
    public ImageFileController(ImageFileService imageFileService) {
        this.imageFileService = imageFileService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseFile>> getImages() {
        List<ResponseFile> files = imageFileService.getImages().map(image -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images")
                    .path(String.valueOf(image.getId()))
                    .toUriString();

            return new ResponseFile(
                    image.getName(),
                    fileDownloadUri,
                    image.getType(),
                    image.getData().length
            );
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        ImageFile imageFile = imageFileService.getImage(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "Attachment; filename =\"" + imageFile.getName() + "\"")
                .body(imageFile.getData());
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadImage(@RequestBody MultipartFile file) {
        String message = "";
        try {
            imageFileService.storeImage(file);
            Long imageToUpload = imageFileService.getImageByNameEquals(file.getOriginalFilename()).getId();
            message += imageToUpload;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "There was a problem uploading the " + file.getOriginalFilename() + "file!";

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @DeleteMapping("/id")
    public void deleteImage(@PathVariable("id") Long id) {
        imageFileService.deleteImage(id);
    }
}
