package org.example;

import org.example.entity.Person;
import org.example.service.EntityService;
import org.example.service.EntityServiceImpl;
import org.example.utils.AppProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    EntityService<Person, Long> sut;
    AppProperties properties = new AppProperties("/application.properties");

    @BeforeEach
    void setUp() {
        sut = new EntityServiceImpl(properties);
    }

    @Test
    public void shouldSaveEntity() {
        Person entityToSave = getPerson(10L, "Ivan", 29);
        sut.saveEntity(entityToSave);
        Person actual = sut.getEntityById(10L);
        assertEquals(entityToSave, actual);
    }

    @Test
    public void shouldUpdateEntity() {
        Person updated = getPerson(150L, "Ivan", 29);
        sut.saveEntity(updated);
        Person expected = getPerson(150L, "Kirill", 28);
        sut.updateEntity(150L, expected);
        Person actual = sut.getEntityById(150L);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveEntity() {
        Person actual = getPerson(50L, "Ivan", 29);
        sut.saveEntity(actual);
        sut.removeEntity(50L);
        assertTrue(sut.getAllEntities().isEmpty());
    }

    @Test
    public void shouldFindByIdEntity() {
        Person expected = getPerson(100L, "Ivan", 29);
        sut.saveEntity(expected);
        Person actual = sut.getEntityById(100L);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAllEntities() {

        Person expected0 = getPerson(1L, "Ivan", 29);
        Person expected1 = getPerson(2L, "Kolya", 45);
        List<Person> actualList = List.of(expected0, expected1);
        actualList.forEach(
                person -> sut.saveEntity(person)
        );
        List<Person> expectedList = sut.getAllEntities();
        assertEquals(expectedList.size(), actualList.size());
    }


    protected Person getPerson(Long id, String name, int age) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setAge(age);
        return person;
    }

    @AfterEach
    void clear() {
        List<Person> list = sut.getAllEntities();
        list.forEach(person -> sut.removeEntity(person.getId()));
    }
}
