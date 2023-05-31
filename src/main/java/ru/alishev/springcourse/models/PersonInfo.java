package ru.alishev.springcourse.models;

public class PersonInfo {
  private int personId;
  private String personFullName;
  private int year_of_birth;
  private String bookTitle;
  private String bookAuthor;
  private String bookYearOfPublish;

  public int getPersonId() {
    return personId;
  }

  public void setPersonId(int personId) {
    this.personId = personId;
  }

  public String getPersonFullName() {
    return personFullName;
  }

  public void setPersonFullName(String personFullName) {
    this.personFullName = personFullName;
  }

  public int getYear_of_birth() {
    return year_of_birth;
  }

  public void setYear_of_birth(int year_of_birth) {
    this.year_of_birth = year_of_birth;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  public String getBookAuthor() {
    return bookAuthor;
  }

  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }

  public String getBookYearOfPublish() {
    return bookYearOfPublish;
  }

  public void setBookYearOfPublish(String bookYearOfPublish) {
    this.bookYearOfPublish = bookYearOfPublish;
  }
}
