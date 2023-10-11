package vagnerlg.com.github.medicalservices.file.service;

import vagnerlg.com.github.medicalservices.file.File;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    File upload(String fileName, InputStream inputStream) throws IOException;
}
