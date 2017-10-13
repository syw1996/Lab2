package com.zjn.entity;

import java.io.Serializable;

public class Modify implements Serializable {
	 private static final long serialVersionUID = 1L;
	 private String id;
	 private String publisher;
	 private String publishdate;
	 private String price;
	 
	 public String getId() {
		return id; 
	 } 
	 
	 public void setId(String id) {
		 this.id = id;
	 }
	 
	 public String getPublisher() {
		return publisher; 
	 }
	 
	 public void setPublisher(String publisher) {
		 this.publisher = publisher;
	 }
	 
	 public String getPublishdate() {
		return publishdate; 
	 } 
	 
	 public void setPublishdate(String publishdate) {
		 this.publishdate = publishdate;
	 }
	 
	 public String getPrice() {
		return price; 
	 }
	 
	 public void setPrice(String price) {
		 this.price = price;
	 }
	 

}

