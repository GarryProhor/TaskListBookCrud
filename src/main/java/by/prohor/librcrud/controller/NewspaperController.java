package by.prohor.librcrud.controller;


import by.prohor.librcrud.exceptions.LibraryNotFoundException;
import by.prohor.librcrud.model.Newspaper;
import by.prohor.librcrud.services.NewspaperService;
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
public class NewspaperController {
    @Autowired
    private NewspaperService newspaperService;



    @GetMapping("/newspapers")
    public  String showNewspaperList(Model model, @Param("keyword") String keyword){
        List<Newspaper> listNewspapers = newspaperService.listAll(keyword);
        model.addAttribute("listNewspapers", listNewspapers);
        model.addAttribute("keyword", keyword);
        return "newspapers";
    }

    @GetMapping("/newspapers/new")
    public String showNewFormNewspaper(Model model){
        model.addAttribute("newspaper",new Newspaper());
        model.addAttribute("pageTitle", "Add new newspaper");
        return "newspaper-form";
    }

    @PostMapping("/newspapers/save")
    public String saveNewspaper(Newspaper newspaper, RedirectAttributes ra){
        newspaperService.save(newspaper);
        ra.addFlashAttribute("message", "The newspaper has been saved successfully.");
        return "redirect:/newspapers";
    }
    @GetMapping("/newspapers/edit/{id}")
    public String showEditFormNewspaper(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Newspaper newspaper = newspaperService.get(id);
            model.addAttribute("newspaper", newspaper);
            model.addAttribute("pageTitle", "Edit newspaper (ID: " + id + ")");
            return "newspaper-form";
        } catch (LibraryNotFoundException e) {
            ra.addFlashAttribute("message",  e.getMessage());
            return "redirect:/newspapers";
        }
    }

    @GetMapping("/newspapers/delete/{id}")
    public String deleteNewspaper(@PathVariable("id") Integer id,  RedirectAttributes ra){
        try {
            newspaperService.delete(id);
            ra.addFlashAttribute("message", "The newspaper ID " + id + " has been deleted");
        } catch (LibraryNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/newspapers";
    }
}
