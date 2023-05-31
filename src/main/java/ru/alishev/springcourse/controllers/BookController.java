package ru.alishev.springcourse.controllers;

import javax.validation.Valid;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.alishev.springcourse.dao.BookDAO;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

@Controller
@RequestMapping("/book")
public class BookController {

  private final BookDAO bookDAO;
  private final PersonDAO personDAO;

  @Autowired
  public BookController(BookDAO bookDAO, PersonDAO personDAO) {
    this.bookDAO = bookDAO;
    this.personDAO = personDAO;
  }

  @GetMapping()
  public String index(Model model) {
    model.addAttribute("books", bookDAO.getList());
    return "books/index";
  }

  @GetMapping("/new")
  public String newBook(@ModelAttribute("book") Book book) {
    return "books/new";
  }

  @PostMapping()
  public String create(
      @ModelAttribute("book") @Valid Book book,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors())
      return "books/new";

    bookDAO.create(book);
    return "redirect:/book";
  }

  @GetMapping("/{id}/edit")
  public String edit(Model model, @PathVariable("id") int id) {
    model.addAttribute("book", bookDAO.getById(id));
    return "books/edit";
  }

  @PatchMapping("/{id}")
  public String update(
      @ModelAttribute("book") @Valid Book book,
      BindingResult bindingResult,
      @PathVariable("id") int id) {

    if (bindingResult.hasErrors())
      return "books/edit";

    bookDAO.update(id, book);
    return "redirect:/book";
  }

  @GetMapping("/{id}")
  public String info(
      Model model,
      @PathVariable("id") int id,
      @ModelAttribute("person") Person person) {

    Book book = bookDAO.getById(id);

    if (book == null)
      return "books/init";

    Integer personOfBookId = book.getPersonId();

    if (personOfBookId != null) {
      model.addAttribute("owner", personDAO.getById(personOfBookId));
    }

    model.addAttribute("people", personDAO.getList());

    model.addAttribute("book", book);

    return "books/info";
  }

  @PatchMapping("/{id}/new-owner")
  public String setNewOwner(
      @PathVariable("id") int bookId,
      @ModelAttribute("person") Person person) {

    bookDAO.updateBookOwner(bookId, person.getPersonId());

    return "redirect:/book/" + bookId;
  }

  @PatchMapping("/{id}/delete-owner")
  public String deleteOwner(@PathVariable("id") int bookId) {

    bookDAO.deleteOwner(bookId);

    return "redirect:/book/" + bookId;
  }
}
