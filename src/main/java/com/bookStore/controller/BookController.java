package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.dao.UserRepository;
import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;
import com.bookStore.entity.User;
import com.bookStore.helper.Message;
import com.bookStore.repository.BookRepository;

//import com.smart.entites.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BookController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService myBookService;
	
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister(Model model)
	{
		model.addAttribute("book",new Book());
		
			return"bookRegister";
		}
		
		
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook()
	{
		List<Book>list=service.getAllBook();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book",list);
		return new ModelAndView("bookList","book",list);
	}
	
	@PostMapping("/save")
	public String addBook(@Valid @ModelAttribute("book")Book book,BindingResult result2,Model model,HttpSession session)
	{
		
		
		if(result2.hasErrors())
		{
			System.out.println("Error..!!"+result2.toString());
			model.addAttribute("book", book);
		
			return "bookRegister";
		}
		
		System.out.println("book"+book);
		Book result =this.bookRepository.save(book);
		model.addAttribute("book", new Book());	
		return "redirect:available_books?success";
	}
	
	@RequestMapping("/register")
	public String register(Model model)
	{
		model.addAttribute("title", "Register - User");
		model.addAttribute("user",new User());
		
			return"register";
		}
	
	
	@PostMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user")User user,BindingResult result1, @RequestParam(value="agreement",defaultValue ="false")boolean agreement,Model model,HttpSession session)
	{
		try {
			
			if(result1.hasErrors())
			{
				/* System.out.println("ERORR"+result1.toString()); */
				model.addAttribute("user",user);
				return "register";
			}
		
			if(!agreement)
			{
				System.out.println("You Have Not agreed the terms and conditions");
				throw new Exception("You Have Not agreed the terms and conditions");
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			
			
			  System.out.println("Agreement " +agreement); 
			  System.out.println("User"+user);
			 
			User result= this.userRepository.save(user);
			
			
			model.addAttribute("user",new User());
			
			//session.setAttribute("message", new Message("SucessFully Registerd !!", "alert-success"));
			return "redirect:register?success";
		
		}catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("user",user);
			//session.setAttribute("message", new Message("Something Went Wrong..!!"+e.getMessage(), "alert-danger"));
			return "redirect:register?error";
		}
		
	}
		
		
		
	@GetMapping("/my_books")
	public String getMyBooks(Model model)
	{
		List<MyBookList>list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id)
	
	{
		Book b=service.getBookById(id);
	MyBookList	 mb = new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model)
	{
		Book b=service.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id)
	{
		service.deleteById(id);
		return "redirect:/available_books";
	}

}
