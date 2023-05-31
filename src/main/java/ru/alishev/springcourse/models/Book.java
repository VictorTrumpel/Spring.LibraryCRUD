package ru.alishev.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

public class Book {
  private int bookId;

  @Nullable
  private Integer personId;

  @NotEmpty(message = "Title should not be empty")
  @Size(min = 1, max = 200, message = "Title should be between 1 and 200 characters")
  private String title;

  @NotEmpty(message = "Author should not be empty")
  @Size(min = 1, max = 200, message = "Author should be between 1 and 200 characters")
  private String author;

  @Min(value = 1900, message = "Year of berth can't be early than 1900")
  private int yearOfPublish;

  public Book() {

  }

  public Book(int bookId, String title, String author, int yearOfPublish) {
    this.bookId = bookId;
    this.title = title;
    this.author = author;
    this.yearOfPublish = yearOfPublish;
  }

  public Integer getPersonId() {
    return personId;
  }

  public void setPersonId(Integer personId) {
    this.personId = personId;
  }

  public int getBookId() {
    return this.bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getYearOfPublish() {
    return this.yearOfPublish;
  }

  public void setYearOfPublish(int yearOfPublish) {
    this.yearOfPublish = yearOfPublish;
  }
}
