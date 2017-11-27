/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.tele2ssc.easybuy.services;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import lv.tele2ssc.easybuy.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 *
 * @author emilbikj
 */
@Service
public class ImageService {
    
    @Value("${image.storage.path}")
     private String imageStoragePath;
     @Autowired
     private GoodsService goodsService;
 
     public Resource loadImageAsResource(long goodsId) {
         Goods goods = goodsService.findGoodById(goodsId);
         String imageFileName = goods.getImageFileName();
         Path resourceFilePath = imageFileName == null ? null : Paths.get(imageStoragePath, String.valueOf(goodsId), imageFileName);
         if (resourceFilePath == null || !Files.exists(resourceFilePath)) {
             return null;
         }
         Resource result = new FileSystemResource(resourceFilePath.toFile());
         return result;
     }
     
     public void store(Goods goods, MultipartFile file) throws IOException {
         String imageFileName = file.getOriginalFilename();
         Path resourceFilePath = Paths.get(imageStoragePath, String.valueOf(goods.getId()), imageFileName);
         Files.createDirectories(resourceFilePath.getParent());
         file.transferTo(resourceFilePath.toFile());
     }
    
}
