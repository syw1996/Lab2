package com.zjn.entity;

import java.io.Serializable;

public class Book implements Serializable {
	 private static final long serialVersionUID = 1L;
	 private Integer isbn;
	 private String title;
	 private Integer id;
	 private String publisher;
	 private Integer publishdate;
	 private float price;
	 
	 public Integer getIsbn() {
		return isbn; 
	 }
	 
	 public void setIsbn(Integer isbn) {
		 this.isbn = isbn;
	 }
	 
	 public String getTitle() {
		return title; 
	 }
	 
	 public void setTitle(String title) {
		 this.title = title;
	 }
	 
	 public Integer getId() {
		return id; 
	 } 
	 
	 public void setId(Integer id) {
		 this.id = id;
	 }
	 
	 public String getPublisher() {
		return publisher; 
	 }
	 
	 public void setPublisher(String publisher) {
		 this.publisher = publisher;
	 }
	 
	 public Integer getPublishdate() {
		return publishdate; 
	 } 
	 
	 public void setPublishdate(Integer publishdate) {
		 this.publishdate = publishdate;
	 }
	 
	 public float getPrice() {
		return price; 
	 }
	 
	 public void setPrice(float price) {
		 this.price = price;
	 }
	 

}
