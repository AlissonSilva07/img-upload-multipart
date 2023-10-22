package edu.alisson.imgupload.repository;

import edu.alisson.imgupload.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageData, String> {
}
