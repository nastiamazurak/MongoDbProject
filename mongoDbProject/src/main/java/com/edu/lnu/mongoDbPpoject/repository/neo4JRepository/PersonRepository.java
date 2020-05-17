package com.edu.lnu.mongoDbPpoject.repository.neo4JRepository;

import com.edu.lnu.mongoDbPpoject.model.Person;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PersonRepository extends Neo4jRepository<Person,String> {

    @Query("MATCH (p:Person)-[:Follows]-(friend)<-[:Follows]-(foaf:Person) " +
            "WHERE (p.nickName = $person1) " +
            "AND (foaf.nickName= $person2) " +
            "RETURN friend.nickName")
    List<String> getCommonFriends(@Param("person1")String person1, @Param("person2")String person2);

    @Query("Match p=shortestPath(" +
            "(p1:Person)-[*]-(p2:Person)) WHERE (p1.nickName=$person1)" +
            "And (p2.nickName=$person2)" +
            "RETURN [n IN nodes(p)| n.nickName] ")
    String getConnections(@Param("person1")String person1, @Param("person2")String person2);
    @Query("MATCH(p:Person) RETURN p")
    List<Person> getAll();

    @Query("MATCH (a:Person), (b:Person) WHERE (a.nickName=$whoFollow)" +
            "AND (b.nickName = $whoIsFollowed) CREATE (a)-[r:Follows]->(b)")
    Relationship createRelation(@Param("whoIsFollow")String whoFollow, @Param("whoIsFollowed")String whoIsFollowed);

    @Query("MATCH (a)" +
            "-[r:Follows]->(b) WHERE (a.nickName=$whoFollow) And (b.nickName=$whoIsFollowed)" +
            "DELETE r")
    void deleteRelation(@Param("whoFollow")String whoFollow, @Param("whoIsFollowed")String whoIsFollowed);

}

//RETURN [n IN nodes(p) | n.nickName] as names