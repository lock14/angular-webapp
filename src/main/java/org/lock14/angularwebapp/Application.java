package org.lock14.angularwebapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lock14.angularwebapp.domain.Person;
import org.lock14.angularwebapp.repository.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        PersonRepository personRepository = context.getBean(PersonRepository.class);
        ClassPathResource resource = new ClassPathResource("person.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Person> people = Arrays.asList(mapper.readValue(resource.getInputStream(), Person[].class));
        personRepository.saveAll(people);
    }

}
