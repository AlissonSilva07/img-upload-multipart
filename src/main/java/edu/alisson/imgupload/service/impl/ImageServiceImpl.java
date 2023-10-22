package edu.alisson.imgupload.service.impl;

import edu.alisson.imgupload.entity.ImageData;
import edu.alisson.imgupload.repository.ImageRepository;
import edu.alisson.imgupload.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageData salvarImagem(MultipartFile file) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (filename.contains("..")) {
                throw new Exception("O nome do arquivo contém caracteres não permitidos " + filename);
            }

            ImageData imageData =
                    new ImageData(filename,
                            file.getContentType(),
                            file.getBytes() );

            return imageRepository.save(imageData);

        } catch (Exception e) {
             throw new Exception("Não foi possível salvar o arquivo " + filename);
        }
    }

    @Override
    public List<ImageData> verTodasImagens() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<ImageData> buscarPorId(String idDaImagem) {
        return imageRepository.findById(idDaImagem);
    }

    @Override
    public ImageData downloadImagem(String idDaImagem) throws Exception {
        return imageRepository
                .findById(idDaImagem)
                .orElseThrow(() -> new Exception("Não foi possível acahar a imagem com o id " + idDaImagem));
    }
}
