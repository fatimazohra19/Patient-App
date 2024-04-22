package com.example.demo.document.service;

import com.example.demo.document.entity.Document;
import com.example.demo.document.config.StorageProperties;
import com.example.demo.document.dao.DocumentRepository;
import com.example.demo.document.exceptions.FileNotFoundException;
import com.example.demo.document.exceptions.StorageException;
import com.example.demo.document.dto.DocumentDto;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private Path rootLocation; // Removed @Autowired here
    private final StorageProperties storageProperties;
    private final DocumentRepository documentRepository;

    @PostConstruct
    public void init() {
        try {
            if (!StringUtils.hasText(storageProperties.getLocation())) {
                throw new StorageException("File upload location cannot be empty.");
            }

            this.rootLocation = Paths.get(storageProperties.getLocation()).toAbsolutePath().normalize();
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            Document document = documentRepository.save(Document.builder()
                    .filename(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .build());

            Path destinationFile = this.rootLocation.resolve(Paths.get(document.getId().toString()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            return document.getId();
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    public Path load(String filename) {

       return rootLocation.resolve(filename);
    }

    public DocumentDto loadAsResource(String id) {
        Document document = documentRepository.findById(id).orElseThrow(FileNotFoundException::new);
        try {
            Path file = load(id);
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new FileNotFoundException("Could not read file: " + id);
            }
            return DocumentDto.builder()
                    .filename(document.getFilename())
                    .size(document.getSize())
                    .contentType(document.getContentType())
                    .resource(resource)
                    .build();
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + id, e);
        }
    }
//            UrlResource resource = new UrlResource(file.toUri());
//            if (!resource.exists() || !resource.isReadable()) {
//                throw new FileNotFoundException(
//                        "Couldn't read file: " + id);
//            }
//            return DocumentDto.builder()
//                    .filename(document.getFilename())
//                    .size(document.getSize())
//                    .contentType(document.getContentType())
//                    .resource((Resource)resource)
//                    .build();
//        } catch (MalformedURLException e) {
//            throw new FileNotFoundException("Could not read file: " + id, e);
//        }
//    }

    public void delete(String id) {
        documentRepository.deleteById(id);
    }
}