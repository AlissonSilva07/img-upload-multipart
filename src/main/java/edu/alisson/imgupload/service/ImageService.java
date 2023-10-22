package edu.alisson.imgupload.service;

import edu.alisson.imgupload.entity.ImageData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    ImageData salvarImagem(MultipartFile file) throws Exception;

    List<ImageData> verTodasImagens();

    Optional<ImageData> buscarPorId(String idDaImagem);
}
