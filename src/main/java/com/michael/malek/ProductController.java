package com.michael.malek;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/addProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addImage(@RequestParam("file") MultipartFile file, @RequestParam String name, @RequestParam double price, @RequestParam String description) {
		return productService.saveProduct(file, name, price, description);
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<List<FileResource>> giveAllProducts(){
		return productService.getAllProducts();
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		return productService.deleteProductById(id);
	}
	
	@PutMapping("/editProduct")
	public ResponseEntity<String> putImage(@RequestParam int id, @RequestParam String name, @RequestParam double price, @RequestParam String description) {
		return productService.updateProduct(id, name, price, description);
	}
}
