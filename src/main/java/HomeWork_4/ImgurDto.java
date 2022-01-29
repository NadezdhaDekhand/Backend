package HomeWork_4;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImgurDto {
    @JsonProperty("data")
    public IngurData data;
    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("status")
    public Integer status;

}
