package com.michael.malek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/addImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addImage(@RequestParam("file") MultipartFile file, @RequestParam String name, @RequestParam double price, @RequestParam String description) {
		return productService.saveImage(file, name, price, description);
	}

}
