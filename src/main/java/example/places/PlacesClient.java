package example.places;

import example.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class PlacesClient {

    private final RestTemplate restTemplate;
    private final String placesServiceUrl;
    private final String placesServiceApiKey;

    @Autowired
    public PlacesClient(final RestTemplate restTemplate,
                        @Value("${places.url}") final String placesServiceUrl,
                        @Value("${places.api_secret}") final String placesServiceApiKey) {
        this.restTemplate = restTemplate;
        this.placesServiceUrl = placesServiceUrl;
        this.placesServiceApiKey = placesServiceApiKey;
    }

    public Optional<PlacesResponse> fetchPlaces(Person costumer) {
        String url = placesServiceUrl + "/json?location={long},{lat}&radius={radius}&keyword=coffee&key={api-key}";
        try {
            return Optional.ofNullable(restTemplate.getForObject(url, PlacesResponse.class, costumer.getHomeLongitude(), costumer.getHomeLatitude(), "1500", placesServiceApiKey));

        } catch (RestClientException e) {
            return Optional.empty();
        }
    }
}
