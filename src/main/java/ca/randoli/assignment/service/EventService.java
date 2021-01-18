package ca.randoli.assignment.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    public List<String> getEventNames() {
        List<String> names = new ArrayList<>();
        names.add("Kasun");
        names.add("Vickmal");

        return names;
    }
}
