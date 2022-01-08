package com.michael.malek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	public ResponseEntity<?> saveImage(MultipartFile file, String name, double price, String description) {
		boolean fileIsValid = Utils.isValidFile(file);
		if (!fileIsValid) {
			return ResponseEntity.badRequest().body("Invalid file type.");
		}
		String fileLocation;
		try {
			fileLocation = Utils.saveImageToDisk(file);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setFilePath(fileLocation);
		repo.save(product);
		return ResponseEntity.ok("File Uploaded Successfully");
	}
	

}
