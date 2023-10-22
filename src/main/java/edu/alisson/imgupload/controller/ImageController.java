package edu.alisson.imgupload.controller;

import edu.alisson.imgupload.entity.ImageData;
import edu.alisson.imgupload.model.ResponseData;
import edu.alisson.imgupload.service.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseData uploadImagem(@RequestParam("file") MultipartFile file) throws Exception {
        ImageData imageData = null;
        imageData = imageService.salvarImagem(file);

        return new ResponseData(imageData.getFileName(), file.getContentType(), file.getSize());
    }
}
