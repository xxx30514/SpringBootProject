package mysite.cardstore.controller;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import mysite.cardstore.controller.utils.Result;

/**
 * 
 * @author yeh
 * 檔案上傳下載
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
	@Value("${cardstore.path}")
	private String basePath;
	
	@PostMapping("/upload")
	public Result upload(MultipartFile headerImg){
		log.info(headerImg.toString());
		//獲取上傳的檔案名稱
		String originalFilename = headerImg.getOriginalFilename();
		if (!headerImg.isEmpty()) {	
			//獲取上傳的檔案的後綴名 ex. jpg
			String typeName =  originalFilename.substring(originalFilename.lastIndexOf("."));
			//獲取UUID
			String uuid = UUID.randomUUID().toString();
			//拼接新檔名避免重複檔名問題
			originalFilename = uuid + typeName;
		}
		//創建目錄
		File file = new File(basePath);
		if (!file.exists()) {
			//若無對應目錄則新建指定目錄
			file.mkdirs();
		}
		try {
			if (!headerImg.isEmpty()) {				
				headerImg.transferTo(new File(basePath+originalFilename));
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Result(true,originalFilename);
	}
	
	@GetMapping("/download")
	public void download(String name,HttpServletResponse response) {
		
	}
}
