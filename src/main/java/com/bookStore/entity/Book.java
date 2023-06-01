package com.bookStore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Author Name Can not be Empty..!!")
	private String author;
	@NotBlank(message = "Name Can not be Empty..!!")
	private String name;
	@NotBlank(message = "Price Can not be Empty..!!")
	private String price;
	
	@ManyToOne
	private User user;
	
	
	public Book(int id,String author,String name ,String price) {
		super();
		this.id = id;
		this.author = author;
		this.name = name;
		this.price = price;
	}


	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", name=" + name + ", price=" + price + "]";
	}


	
	
	
	
	
	
	

}
