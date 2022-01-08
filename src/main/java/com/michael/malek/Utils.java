package com.michael.malek;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
	public static boolean isValidFile(MultipartFile file){
		String fileName = file.getOriginalFilename().toLowerCase();
		if (fileName.endsWith(".png") || fileName.endsWith(".jpeg") || fileName.endsWith(".jpg")) {
			return true;
		}
		return false;
	}
	
	// Saves given file to the disk. 
	// Returns the complete path where the file was stored
	public static String saveImageToDisk(MultipartFile file) throws Exception{
		Utils.checkPath();
		String fileName = file.getOriginalFilename();
		byte[] bytes = file.getBytes();
		Path path = Paths.get("C:\\upload\\" + new Date().getTime() + "-" + fileName);
		Files.write(path, bytes);
		return path.toString();
	}
	
	// Helper method to ensure that the directory where the files will be stored exists
	public static void checkPath() {
		new File("C:\\upload").mkdirs();		 
	}
	
	public static FileResource productToFileResource(Product product) {
		FileResource fileResource = new FileResource();
		byte[] bytes = null;
		if (product!=null) {
			String fileName = product.getFilePath();
			try {
				bytes = Files.readAllBytes(Paths.get(fileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String encodedString = Base64.getEncoder().encodeToString(bytes);
		fileResource.setDescription(product.getDescription());
		fileResource.setPrice(product.getPrice());
		fileResource.setEncodedContent(encodedString);
		fileResource.setId(product.getId());
		fileResource.setName(product.getName());
		return fileResource;
	}
}
