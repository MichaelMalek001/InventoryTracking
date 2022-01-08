package com.michael.malek;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	public ResponseEntity<?> saveProduct(MultipartFile file, String name, double price, String description) {
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
		return ResponseEntity.ok("Product Uploaded Successfully");
	}
	
	public ResponseEntity<List<FileResource>> getAllProducts(){
		List<Product> allProducts =  repo.findAll();
		List<FileResource> allFileResources = new LinkedList<FileResource>(); 
		for (Product product : allProducts) {
			FileResource fileResource = Utils.productToFileResource(product);
			allFileResources.add(fileResource);
		}
		return ResponseEntity.ok().body(allFileResources);
	}
	
	public ResponseEntity<String> deleteProductById(int id) {
		Product product = repo.findById(id).orElse(null);
		if(product!=null) {
			// Delete image file from folder
			File fileToDelete = new File(product.getFilePath());
			try {
				fileToDelete.delete();
			} catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
			// Delete image file from database
			repo.deleteById(id);
			return 	ResponseEntity.ok().body("Product with id " + id + " has been removed.");
		}
		else {
			return ResponseEntity.badRequest().body("Product with id " + id + " does not exist.");
		}
	}
	
	public ResponseEntity<String> updateProduct(int id, String name, double price, String description) {
		Product newProduct = repo.findById(id).orElse(null);
		
		// check if product id given is valid
		if(newProduct==null) {
			return ResponseEntity.badRequest().body("Invalid request.");
		}
		newProduct.setName(name);
		newProduct.setPrice(price);
		newProduct.setDescription(description);
		repo.save(newProduct);
		return 	ResponseEntity.ok().body("Product with id " + newProduct.getId() + " has been updated.");
	}
}
