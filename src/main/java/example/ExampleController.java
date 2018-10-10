package example;

import example.person.Person;
import example.person.PersonRepository;
import example.places.PlacesClient;
import example.places.PlacesResponse;
import example.weather.WeatherClient;
import example.weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ExampleController {

    private final PersonRepository personRepository;
    private final WeatherClient weatherClient;
    private final PlacesClient placesClient;

    @Autowired
    public ExampleController(final PersonRepository personRepository, final WeatherClient weatherClient, final PlacesClient placesClient) {
        this.personRepository = personRepository;
        this.weatherClient = weatherClient;
        this.placesClient = placesClient;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/hello/{lastName}")
    public String hello(@PathVariable final String lastName) {
        Optional<Person> foundPerson = personRepository.findByLastName(lastName);

        return foundPerson
                .map(person -> String.format("Hello %s %s! Now I see you at %s, %s.", person.getFirstName(), person.getLastName(), person.getHomeLatitude(), person.getHomeLongitude()))
                .orElse(String.format("Who is this '%s' you're talking about?", lastName));
    }

    @GetMapping(value = "/hello/{lastName}/location", produces = "application/json")
    public Map location(@PathVariable final String lastName) {
        Optional<Person> foundPerson = personRepository.findByLastName(lastName);

        Map<String, String> response = new HashMap<>();

        if (foundPerson.isPresent()) {
            Person person = foundPerson.get();
            response.put("longitude", person.getHomeLongitude());
            response.put("latitude", person.getHomeLatitude());
            return response;
        }
        response.put("error", String.format("Who is this '%s' you're talking about?", lastName));
        return response;
    }

    @GetMapping("/weather")
    public String weather() {
        return weatherClient.fetchWeather()
                .map(WeatherResponse::getSummary)
                .orElse("Sorry, I couldn't fetch the weather for you :(");
    }

    @GetMapping(value = "/coffee-places-nearby/{lastName}", produces = "application/json")
    public List coffee_places_nearby(@PathVariable final String lastName) {
        Optional<Person> foundPerson = personRepository.findByLastName(lastName);
        List<Map<String, String>> response = new ArrayList<>();
        Map<String,String> error = new HashMap<>();

        if (foundPerson.isPresent()) {
            Person person = foundPerson.get();
            Optional<PlacesResponse> places = placesClient.fetchPlaces(person);
            if (places.isPresent()) {
                List<PlacesResponse.Result> results = places.get().getResults();
                if (((List) results).size() > 0) {
                    for(PlacesResponse.Result res : results) {
                        Map<String,String> found = new HashMap<>();
                        found.put("name", res.getName());
                        found.put("address", res.getVicinity());
                        response.add(found);
                    }
                    return response;
                }
                error.put("error", "No places have been found.");
                response.add(error);
                return response;
            }
            error.put("error", "Internal error. Controller error.");
            response.add(error);
            return response;
        }
        error.put("error", String.format("Who is this '%s' you're talking about?", lastName));
        response.add(error);
        return response;
    }

}