package edu.alisson.imgupload.service;

import edu.alisson.imgupload.entity.ImageData;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageData salvarImagem(MultipartFile file) throws Exception;
    ImageData downloadImagem(String fileId) throws Exception;
}
