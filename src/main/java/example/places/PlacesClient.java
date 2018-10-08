package example.places;

import example.person.Person;
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

    public PlacesClient(final RestTemplate restTemplate,
                        @Value("${places.url}") final String placesServiceUrl,
                        @Value("${places.api_secret}") final String placesServiceApiKey) {
        this.restTemplate = restTemplate;
        this.placesServiceUrl = placesServiceUrl;
        this.placesServiceApiKey = placesServiceApiKey;
    }

    public Optional<PlacesResponse> fetchPlaces(Person costumer) {
        String url = String.format(placesServiceUrl, costumer.getHomeLongitude(), costumer.getHomeLatitude(), placesServiceApiKey);

        try {
            return Optional.ofNullable(restTemplate.getForObject(url, PlacesResponse.class));
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }
}
