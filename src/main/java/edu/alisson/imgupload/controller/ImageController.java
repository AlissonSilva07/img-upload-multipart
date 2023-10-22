package edu.alisson.imgupload.controller;

import edu.alisson.imgupload.entity.ImageData;
import edu.alisson.imgupload.model.ResponseData;
import edu.alisson.imgupload.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/view/{id}")
    public ResponseEntity<Optional<ImageData>> verImagemPorId(@PathVariable(name = "id") String idDaImagem) {
        return ResponseEntity.ok(imageService.buscarPorId(idDaImagem));
    }

    @PostMapping("/upload")
    public ResponseData uploadImagem(@RequestParam("file") MultipartFile file) throws Exception {
        ImageData imageData = null;
        imageData = imageService.salvarImagem(file);

        return new ResponseData(imageData.getFileName(), file.getContentType(), file.getSize());
    }
}
