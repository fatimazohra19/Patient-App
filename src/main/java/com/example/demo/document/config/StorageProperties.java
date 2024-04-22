package com.example.demo.document.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("storage")
@Data
@RequiredArgsConstructor
public class StorageProperties {

	private String location = "upload-dir";



}