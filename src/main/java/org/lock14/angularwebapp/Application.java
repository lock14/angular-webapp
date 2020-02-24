package org.lock14.angularwebapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lock14.angularwebapp.persistence.AddressEntity;
import org.lock14.angularwebapp.persistence.PersonEntity;
import org.lock14.angularwebapp.persistence.StateEntity;
import org.lock14.angularwebapp.repository.AddressRepository;
import org.lock14.angularwebapp.repository.PersonRepository;
import org.lock14.angularwebapp.repository.StateRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        ObjectMapper mapper = new ObjectMapper();

        ClassPathResource resource = new ClassPathResource("states.json");
        StateRepository stateRepository = context.getBean(StateRepository.class);
        List<StateEntity> stateEntities = Arrays.asList(mapper.readValue(resource.getInputStream(), StateEntity[].class));
        stateRepository.saveAll(stateEntities);

        resource = new ClassPathResource("addresses.json");
        AddressRepository addressRepository = context.getBean(AddressRepository.class);
        List<AddressEntity> addressEntities = Arrays.asList(mapper.readValue(resource.getInputStream(), AddressEntity[].class));
        addressRepository.saveAll(addressEntities);

        resource = new ClassPathResource("people.json");
        PersonRepository personRepository = context.getBean(PersonRepository.class);
        List<PersonEntity> people = Arrays.asList(mapper.readValue(resource.getInputStream(), PersonEntity[].class));
        personRepository.saveAll(people);


    }

}
