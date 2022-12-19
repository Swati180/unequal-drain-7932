package com.treehouse.service.Impl;

import com.treehouse.Repo.AdminLoginRepo;
import com.treehouse.exception.AdminException;
import com.treehouse.exception.CustomerException;
import com.treehouse.model.AdminLogin;
import com.treehouse.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@RestController
public class fileUploadServiceImpl {
    @Autowired
    private AdminLoginRepo adminLoginRepo;
    @PostMapping("/product/file")
    public ResponseEntity<Message> fileUploadingHandller(@RequestParam("file") MultipartFile file, @RequestHeader("token") String token) throws  IOException, CustomerException {
        if (file.isEmpty()) {
            throw new CustomerException("file is empty");
        }

      fileUpload(file, token);
        Message loginKeyDto=new Message("file uploaded successfully");
        return new ResponseEntity<Message>(loginKeyDto, HttpStatus.CREATED);


    }
    public void fileUpload(MultipartFile file,String key) throws CustomerException, IOException {
       AdminLogin admindata= adminLoginRepo.findByKey(key);
       if(admindata!=null){
           String pathName=new ClassPathResource("").getFile().getAbsolutePath();
           System.out.println(pathName);
           Files.copy(file.getInputStream(), Paths.get(pathName+"/static/images"+ File.separator+file.getOriginalFilename()) , StandardCopyOption.REPLACE_EXISTING);
//				String download= ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString();
        return;
       }
       throw new CustomerException("Token is not valid");
    }


}
