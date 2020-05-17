package com.edu.lnu.mongoDbPpoject.service.neo4JService;

import com.edu.lnu.mongoDbPpoject.model.Person;
import java.util.List;


public interface PersonService {

    String getConnectionsPath(String nickName);
    List<String> getCommonFriends(String nickName);
    void addConnection(String whoFollow, String whoIsFollowed);
    void deleteConnection(String whoFollow, String whoIsFollowed);
    void addPerson(Person person1);
    void deletePerson(Person person1);
}
