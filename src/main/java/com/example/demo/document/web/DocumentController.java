package com.example.demo.document.web;

import com.example.demo.document.service.DocumentService;
import com.example.demo.document.dto.DocumentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {
    //private final StorageSer
    // vice storageService;

    private final DocumentService documentService;



    //Download
        @GetMapping("/{filename:.+}")
        @ResponseBody
        public ResponseEntity<Resource> download(@PathVariable String filename) {
            DocumentDto documentDto = documentService.loadAsResource(filename);

            return      ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentDto.getFilename() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE,documentDto.getContentType())
                    .header(HttpHeaders.CONTENT_LENGTH,documentDto.getSize().toString())
                    .body((Resource) documentDto.getResource());
        }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) {
        String filename = documentService.store(file);
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/")
                .path(filename)
                .toUriString();
    }

    @DeleteMapping("/{filename:.+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String filename) {
        documentService.delete(filename);
    }



}