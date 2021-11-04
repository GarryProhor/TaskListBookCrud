package by.prohor.librcrud.controller;

import by.prohor.librcrud.exceptions.LibraryNotFoundException;
import by.prohor.librcrud.model.Book;
import by.prohor.librcrud.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class BookController {
    @Autowired private  BookService bookService;



    @GetMapping("/books")
    public  String showBookList(Model model, @Param("keyword") String keyword){

        List<Book> listBooks = bookService.listAll(keyword);
        model.addAttribute("listBooks", listBooks);
        model.addAttribute("keyword", keyword);
        return "books";
    }

    @GetMapping("/books/new")
    public String showNewFormBook(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("pageTitle", "Add new book");
        return "book-form";
    }

    @PostMapping("/books/save")
    public String saveBook(Book book, RedirectAttributes ra){
        bookService.save(book);
        ra.addFlashAttribute("message", "The book has been saved successfully.");
        return "redirect:/books";
    }
    @GetMapping("/books/edit/{id}")
    public String showEditFormBook(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Book book = bookService.get(id);
            model.addAttribute("book", book);
            model.addAttribute("pageTitle", "Edit book (ID: " + id + ")");
            return "book-form";
        } catch (LibraryNotFoundException e) {
            ra.addFlashAttribute("message",  e.getMessage());
            return "redirect:/books";
        }
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id,  RedirectAttributes ra){
        try {
            bookService.delete(id);
            ra.addFlashAttribute("message", "The book ID " + id + " has been deleted");
        } catch (LibraryNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/books";
    }
}
