package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getList() {
        return jdbcTemplate.query(
                "SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getById(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM person WHERE person_id=?",
                new Object[] { id },
                new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void create(Person person) {
        jdbcTemplate.update(
                "INSERT INTO person (full_name, year_of_birth) VALUES(?, ?)",
                person.getFullName(),
                person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET full_name=?, year_of_birth=? WHERE person_id=?",
                updatedPerson.getFullName(),
                updatedPerson.getYearOfBirth(),
                id);
    }

    public void delete(int personId) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", personId);
    }

    public List<Book> getBookListOfUserById(int personId) {
        return jdbcTemplate.query(
                "SELECT * FROM book WHERE person_id=?",
                new Object[] { personId },
                new BeanPropertyRowMapper<>(Book.class));
    }
}
