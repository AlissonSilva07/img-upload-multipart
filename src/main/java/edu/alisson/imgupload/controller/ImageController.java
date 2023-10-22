package edu.alisson.imgupload.controller;

import edu.alisson.imgupload.entity.ImageData;
import edu.alisson.imgupload.model.ResponseData;
import edu.alisson.imgupload.service.ImageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/image-api")
@CrossOrigin(origins = "*")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/view")
    public ResponseEntity<List<ImageData>> verTodasImagens() {
        return ResponseEntity.ok(imageService.verTodasImagens());
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Optional<ImageData>> verImagemPorId(@PathVariable String fileName) {
        return ResponseEntity.ok(imageService.buscarPorId(fileName));
    }

    @PostMapping("/upload")
    public ResponseData uploadImagem(@RequestParam("file") MultipartFile file) throws Exception {
        ImageData imageData = null;
        String downloadURL = null;
        imageData = imageService.salvarImagem(file);
        downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(imageData.getId())
                .toUriString();

        return new ResponseData(imageData.getFileName(), downloadURL, file.getContentType(), file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadImagem(@PathVariable String fileId) throws Exception {
        ImageData imageData = null;
        imageData = imageService.downloadImagem(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageData.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"imageData; filename=\"" + imageData.getFileName() + "\"")
                .body(new ByteArrayResource(imageData.getData()));
    }
}
