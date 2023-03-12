package com.example.server.model.vo.Contents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class imgInfo {
    private MultipartFile raw;
    private  int size;

}
