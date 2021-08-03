package org.example;

import org.example.entity.Person;
import org.example.service.EntityService;
import org.example.service.EntityServiceImpl;
import org.example.service.EntityServiceImplEm;
import org.example.utils.AppProperties;

public class App {

    public static void main(String[] args) {

        AppProperties appProperties = new AppProperties("/application.properties");

        EntityService<Person, Long> entityService = new EntityServiceImpl(appProperties);

        Person person = new Person();
        person.setId(0L);
        person.setName("Nastya");
        person.setAge(26);
        entityService.saveEntity(person);

        System.out.println(entityService.getAllEntities());

        /* Не работает сохранение при использовании EntityManagerFactory
        entityService = new EntityServiceImplEm(appProperties);
        person = new Person();
        person.setId(1L);
        person.setName("Ivan");
        person.setAge(29);
        entityService.saveEntity(person);

        System.out.println(entityService.getAllEntities());*/
    }
}
