package com.eLearn.app.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    public String saveFile(MultipartFile file, String outputPath, String fileName) throws IOException;
}
