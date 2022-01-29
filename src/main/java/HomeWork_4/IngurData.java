package HomeWork_4;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IngurData {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("url")
    public String url;
    @JsonProperty("bio")
    public Object bio;
    @JsonProperty("avatar")
    public String avatar;
    @JsonProperty("avatar_name")
    public String avatarName;
    @JsonProperty("cover")
    public String cover;
    @JsonProperty("cover_name")
    public String coverName;
    @JsonProperty("reputation")
    public Integer reputation;
    @JsonProperty("reputation_name")
    public String reputationName;
    @JsonProperty("created")
    public Integer created;
    @JsonProperty("pro_expiration")
    public Boolean proExpiration;
    @JsonProperty("user_follow")
    public UserFollow userFollow;
    @JsonProperty("is_blocked")
    public Boolean isBlocked;
}
