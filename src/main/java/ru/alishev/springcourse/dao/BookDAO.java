package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;

import ru.alishev.springcourse.models.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Component
public class BookDAO {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public BookDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Book> getList() {
    return jdbcTemplate.query(
        "SELECT * FROM Book",
        new BeanPropertyRowMapper<>(Book.class));
  }

  public Book getById(int id) {
    return jdbcTemplate.query(
        "SELECT * FROM book WHERE book_id=?",
        new Object[] { id },
        new BeanPropertyRowMapper<>(Book.class))
        .stream()
        .findAny()
        .orElse(null);
  }

  public void create(Book book) {
    jdbcTemplate.update(
        "INSERT INTO book (title, author, year_of_publish) VALUES(?, ?, ?)",
        book.getTitle(),
        book.getAuthor(),
        book.getYearOfPublish());
  }

  public void update(int id, Book updateBook) {
    jdbcTemplate.update("UPDATE Book SET title=?, author=?, year_of_publish=? WHERE book_id=?",
        updateBook.getTitle(),
        updateBook.getAuthor(),
        updateBook.getYearOfPublish(),
        id);
  }

  public void updateBookOwner(int book_id, int personId) {
    jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?",
        personId,
        book_id);
  }

  public void deleteOwner(int book_id) {
    jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE book_id=?", book_id);
  }

  public void delete(int bookId) {
    jdbcTemplate.update("DELETE FROM book WHERE book_id=?", bookId);
  }

}
