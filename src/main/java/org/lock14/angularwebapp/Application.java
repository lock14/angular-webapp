package org.lock14.angularwebapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lock14.angularwebapp.domain.Address;
import org.lock14.angularwebapp.domain.Person;
import org.lock14.angularwebapp.domain.State;
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
        List<State> states = Arrays.asList(mapper.readValue(resource.getInputStream(), State[].class));
        stateRepository.saveAll(states);

        resource = new ClassPathResource("addresses.json");
        AddressRepository addressRepository = context.getBean(AddressRepository.class);
        List<Address> addresses = Arrays.asList(mapper.readValue(resource.getInputStream(), Address[].class));
        addressRepository.saveAll(addresses);

        resource = new ClassPathResource("people.json");
        PersonRepository personRepository = context.getBean(PersonRepository.class);
        List<Person> people = Arrays.asList(mapper.readValue(resource.getInputStream(), Person[].class));
        personRepository.saveAll(people);


    }

}
