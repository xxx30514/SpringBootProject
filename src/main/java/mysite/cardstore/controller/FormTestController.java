package mysite.cardstore.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author yeh
 * 文件上傳功能測試
 */
@Slf4j
@Controller
public class FormTestController {
	@GetMapping("/form_layouts")
	public String formLayouts() {
		return "form/general";
	}
	@PostMapping("/upload")
	public String upload(@RequestParam("email") String email,
			@RequestParam("username") String username,
			@RequestPart("headerImg") MultipartFile headerImg,
			@RequestPart("productImgs") MultipartFile[] productImgs) throws IllegalStateException, IOException {
		
		log.info("上傳資訊:email={},username={},headerImg={},productImgs={}",
				email,username,headerImg.getSize(),productImgs.length);
		if (!headerImg.isEmpty()) {
			//保存到文件伺服器
			String originalFilename = headerImg.getOriginalFilename();
			headerImg.transferTo(new File("C:\\Users\\yeh\\Desktop\\img\\"+originalFilename));
		}
		if (productImgs.length >0) {
			for (MultipartFile productImg : productImgs) {
				if (!productImg.isEmpty()) {
					String originalFilename = productImg.getOriginalFilename();
					productImg.transferTo(new File("C:\\Users\\yeh\\Desktop\\img\\"+originalFilename));
				}
			}
		}
		return "admin_index";
	}
}
