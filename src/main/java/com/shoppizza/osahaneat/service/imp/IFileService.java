package com.shoppizza.osahaneat.service.imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    boolean saveFile(MultipartFile file);
    Resource loadFile(String fileName);
}
