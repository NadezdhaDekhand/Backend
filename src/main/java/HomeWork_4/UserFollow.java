package HomeWork_4;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserFollow {
    @JsonProperty("status")
    public Boolean status;
}
