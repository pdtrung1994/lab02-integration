package example.places;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacesResponse {

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public PlacesResponse() {
    }

    PlacesResponse(String jsonInput) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            results = mapper.readValue(jsonInput, mapper.getTypeFactory().constructCollectionType(List.class, Result.class));
        } catch (JsonParseException e1) {
            throw new RuntimeException(e1);
        } catch (JsonMappingException e2) {
            throw new RuntimeException(e2);
        } catch (IOException e3) {
            throw new RuntimeException(e3);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlacesResponse that = (PlacesResponse) o;
        return results.containsAll(that.results) && results.size() == that.results.size();
    }

    @Override
    public String toString() {
        return "PlacesResponse{" +
                "results=" + results +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private String name;
        private String vicinity;

        public String getName() {
            return name;
        }

        public String getVicinity() {
            return vicinity;
        }

        public Result() {
        }

        public Result(String name, String vicinity) {
            this.name = name;
            this.vicinity = vicinity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Result result = (Result) o;
            return Objects.equals(name, result.name) &&
                    Objects.equals(vicinity, result.vicinity);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, vicinity);
        }

        @Override
        public String toString() {
            return "Result{" +
                    "name='" + name + '\'' +
                    ", vicinity='" + vicinity + '\'' +
                    '}';
        }
    }
}
