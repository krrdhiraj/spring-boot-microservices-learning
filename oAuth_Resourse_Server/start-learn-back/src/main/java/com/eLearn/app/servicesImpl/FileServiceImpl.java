package com.eLearn.app.servicesImpl;

import com.eLearn.app.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String saveFile(MultipartFile file, String outputPath, String fileName) throws IOException {
        Path path = Paths.get(outputPath);

        // create output folder is not exits
        Files.createDirectories(path);

        // video/audio/image processing (watch youtube video)

        // ffmpeg
//        code insert krna h video dekh k



        // join the path
        Path filePath = Paths.get(path.toString(), file.getOriginalFilename());
        System.out.println(filePath);
        // write files
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }
}