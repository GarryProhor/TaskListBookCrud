package by.prohor.librcrud.controller;

import by.prohor.librcrud.model.Book;
import by.prohor.librcrud.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {
    @Autowired private BookService bookService;

    @GetMapping("/books")
    public  String showBookList(Model model){
        //лог2
        List<Book> listBooks = bookService.listAll();
        model.addAttribute("listBooks", listBooks);

        return "books";
    }

    @GetMapping("/books/new")
    public String showNewForm(Model model){
        model.addAttribute("book",new Book());
        return "book-form";
    }

    @PostMapping("/users/save")
    public String saveBook(Book book, RedirectAttributes ra){
        bookService.save(book);
        ra.addAttribute("message", "The book has been saved successfully.");
        return "redirect:/books";
    }
}
