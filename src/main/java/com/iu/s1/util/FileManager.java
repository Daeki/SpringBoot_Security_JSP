package com.iu.s1.util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	@Autowired
	private ResourceLoader resourceLoader;
	
	public boolean delete(String name,String fileName, HttpSession session) throws Exception {
		//1. 경로 설정
		String path = session.getServletContext().getRealPath("resources/upload/"+name);
		File file = new File(path, fileName);
		boolean check = false;
		if(file.exists()) {
			check= file.delete();
		}
		return check;
	}
	
	public String save(String filePath, MultipartFile multipartFile)throws Exception{
		//filePath : /static/ 제외하고 하위의 경로명
		
		String path = "classpath:/static/";
		
		//resourceLoader.getResource(path);
		
		File file = new File(resourceLoader.getResource(path).getFile(), filePath);
		
		System.out.println(file.getAbsolutePath());
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String fileName=null;
		
		//중복되지 않는 파일 명 생성
		fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		
		file = new File(file, fileName);
		
		FileCopyUtils.copy(multipartFile.getBytes(), file);
		
		
		return fileName;
	}
	
	
	
	
	
	
	
	
	
	public void saveUseTransfer(MultipartFile multipartFile, File file)throws Exception{
		multipartFile.transferTo(file);
	}
	
	public void saveUseCopy(MultipartFile multipartFile, File file)throws Exception{
		FileCopyUtils.copy(multipartFile.getBytes(), file);
	}
	
	public String makeUUIDFileName()throws Exception{
		return UUID.randomUUID().toString();
	}
	
	public String makeTimeFileName()throws Exception{
		Calendar ca = Calendar.getInstance();
		long time = ca.getTimeInMillis();
		return String.valueOf(time);
	}


	public File makePath(HttpSession session)throws Exception{
		//1. 저장할 폴더 지정 /resources/upload/member
		//2. os에서 인식 할 수 있는 실제 경로 찾기
		// jsp : application - server : ServletContext
		String path = session.getServletContext().getRealPath("resources/upload/member");
		System.out.println(path);

		//3. 실제 경로에 폴더가 없으면 파일 저장시 에러 발생
		File file = new File(path);

		if(!file.exists()) {
			file.mkdirs();
		}
		
		return file;

	}

}
