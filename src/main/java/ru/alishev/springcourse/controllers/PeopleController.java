package ru.alishev.springcourse.controllers;

import javax.validation.Valid;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.getList());
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(
            @ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(
            @ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult,
            @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String info(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getById(id));
        model.addAttribute("books", personDAO.getBookListOfUserById(id));
        return "people/info";
    }

    @DeleteMapping("/{id}")
    public String deltePerson(@PathVariable("id") int personId) {
        personDAO.delete(personId);
        return "redirect:/people";
    }
}
