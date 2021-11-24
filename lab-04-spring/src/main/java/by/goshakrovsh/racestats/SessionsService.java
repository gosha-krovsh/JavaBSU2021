package by.goshakrovsh.racestats;

import by.goshakrovsh.racestats.model.Session;
import by.goshakrovsh.racestats.repositories.SessionRepository;
import by.goshakrovsh.racestats.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionsService {
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    TrackRepository trackRepository;

    public List<Session> getLast10Sessions() {
        return sessionRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Session::getDate_time).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public void UpdateRecord(Session session) {
        System.out.println(session.getTrack().getRecord());
        System.out.println(session.getTime());
        if (session.getTrack().getRecord() != null) {
            if (session.getTrack().getRecord().compareTo(session.getTime()) > 0) {
                session.getTrack().setRecord(session.getTime());
                trackRepository.save(session.getTrack());
            }
        } else {
            session.getTrack().setRecord(session.getTime());
            trackRepository.save(session.getTrack());
        }
    }
}
