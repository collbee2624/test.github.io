package com.project.food.foodKind;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/boardMng")
public class BoardController {
	
	@Autowired
	BoardMngService boardMngService;
	
	// 게시판 수정
	@RequestMapping(value="/saveBoardMng", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveBoardMng(@RequestBody Map<String, Object> param) {
		Map<String, Object> resultMap = boardMngService.saveBoardMng(param);
		System.out.println("A");
		return resultMap;
	}
	
	@RequestMapping(value="/upload_files", method=RequestMethod.POST)
    public @ResponseBody String uploadImage(@RequestParam("files") MultipartFile file) throws IOException {
		String fileId = UUID.randomUUID().toString();
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		
		String fileName = fileId + "." + extension;
		File targetFile = new File("src/main/resources/static/images/" + fileName);
        try {
            InputStream fileStream = file.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        return fileName;
    }
	
	@RequestMapping(value="/images/{imagename}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<byte[]> userSearch(@PathVariable("imagename") String imagename) throws IOException {
		System.out.println(imagename);
		InputStream imageStream = new FileInputStream("src/main/resources/static/images/" + imagename);
		byte[] imageByteArray = IOUtils.toByteArray(imageStream);
		imageStream.close();
		
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}
}
