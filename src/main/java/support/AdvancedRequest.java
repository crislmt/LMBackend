package support;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AdvancedRequest {
    private String title;
    private Float maxPrice;
    private Float minPrice;
    private Integer releaseYear;
    private String director;
    private String genre;
    private String actor;
}
