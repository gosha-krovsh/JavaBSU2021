package by.goshakrovsh.racestats.repositories;

import by.goshakrovsh.racestats.model.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Integer> {
    List<Session> findAll();
}
