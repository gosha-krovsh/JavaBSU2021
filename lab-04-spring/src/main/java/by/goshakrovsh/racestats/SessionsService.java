package by.goshakrovsh.racestats;

import by.goshakrovsh.racestats.model.Session;
import by.goshakrovsh.racestats.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionsService {
    @Autowired
    SessionRepository sessionRepository;

    public List<Session> getLast10Sessions() {
        return sessionRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Session::getDate_time).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}
