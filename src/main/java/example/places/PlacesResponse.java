package example.places;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacesResponse {

    public ArrayList<Result> results;

    public PlacesResponse() {
    }

    public PlacesResponse(String name, String vicinity) {
        results = new ArrayList<>();
        results.add(new Result(name, vicinity));
    }

    public PlacesResponse(List<Result> results) {
        this.results = new ArrayList<>();
        this.results.addAll(results);
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlacesResponse that = (PlacesResponse) o;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }

    @Override
    public String toString() {
        return "PlacesResponse{" +
                "results=" + results +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private String name;
        private String vicinity;

        public Result() {
        }

        public Result(String name, String vicinity) {
            this.name = name;
            this.vicinity = vicinity;
        }

        public String getName() {
            return name;
        }

        public String getVicinity() {
            return vicinity;
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
