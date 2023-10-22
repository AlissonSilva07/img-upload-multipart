package edu.alisson.imgupload.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private String filename;
    private String downloadURl;
    private String fileType;
    private Long fileSize;
}
