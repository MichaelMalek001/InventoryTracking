package com.michael.malek;

public class FileResource {
	private String name;
	private String encodedContent;
	private String description;
	private int id;
	private double price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEncodedContent() {
		return encodedContent;
	}
	
	public void setEncodedContent(String encodedContent) {
		this.encodedContent = encodedContent;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "FileResource [name=" + name + ", encodedContent=" + encodedContent + ", description=" + description
				+ ", id=" + id + ", price=" + price + "]";
	}
}