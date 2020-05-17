package com.edu.lnu.mongoDbPpoject.service.neo4JService;

import com.edu.lnu.mongoDbPpoject.model.Person;
import com.edu.lnu.mongoDbPpoject.model.User;
import com.edu.lnu.mongoDbPpoject.repository.neo4JRepository.PersonRepository;
import com.edu.lnu.mongoDbPpoject.service.mongoService.UserServiceImpl;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    UserServiceImpl userService;
    @Override
    public String getConnectionsPath(String nickName) {
        User user = userService.getCurrentUser();
        return personRepository.getConnections(user.getNickName(), nickName);
    }

    @Override
    public List<String> getCommonFriends(String nickName) {
        User user = userService.getCurrentUser();
        Person person1 = new Person();
        Person person2 = new Person();
        person2.setNickName(nickName);
        person1.setNickName(user.getNickName());
        return personRepository.getCommonFriends(person1.getNickName(), person2.getNickName());
    }

    @Override
    public void addConnection(String whoFollow, String whoIsFollowed) {
         personRepository.createRelation(whoFollow, whoIsFollowed);
    }

    @Override
    public void deleteConnection(String whoFollow, String whoIsFollowed) {
        personRepository.deleteRelation(whoFollow, whoIsFollowed);
    }

    @Override
    public void addPerson(Person person) {
        personRepository.save(person);

    }

    @Override
    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

}
