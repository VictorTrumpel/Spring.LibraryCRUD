package ru.alishev.springcourse.dao;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.alishev.springcourse.models.Person;

import java.sql.ResultSet;

public class PersonMapper implements RowMapper<Person> {

  @Override
  public Person mapRow(ResultSet resultSet, int i) throws SQLException {
    Person person = new Person();

    person.setId(resultSet.getInt("id"));
    person.setName(resultSet.getString("name"));
    person.setEmail(resultSet.getString("email"));
    person.setAge(resultSet.getInt("age"));

    return person;
  }
}
