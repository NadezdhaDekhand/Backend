package HomeWork_4;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Datum {
    @JsonProperty("id")
    public String id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public String description;
    @JsonProperty("cover")
    public String cover;
    @JsonProperty("cover_width")
    public Integer coverWidth;
    @JsonProperty("cover_height")
    public Integer coverHeight;
    @JsonProperty("width")
    public Integer width;
    @JsonProperty("height")
    public Integer height;
    @JsonProperty("account_url")
    public String accountUrl;
    @JsonProperty("account_id")
    public Integer accountId;
    @JsonProperty("privacy")
    public String privacy;
    @JsonProperty("views")
    public Integer views;
    @JsonProperty("link")
    public String link;
    @JsonProperty("ups")
    public Integer ups;
    @JsonProperty("downs")
    public Integer downs;
    @JsonProperty("points")
    public Integer points;
    @JsonProperty("score")
    public Integer score;
    @JsonProperty("is_album")
    public Boolean isAlbum;
    @JsonProperty("vote")
    public String vote;
    @JsonProperty("favorite")
    public Boolean favorite;
    @JsonProperty("nsfw")
    public Boolean nsfw;
    @JsonProperty("section")
    public Object section;
    @JsonProperty("comment_count")
    public Integer commentCount;
    @JsonProperty("favorite_count")
    public Integer favoriteCount;
    @JsonProperty("topic")
    public String topic;
    @JsonProperty("topic_id")
    public String topicId;
    @JsonProperty("images_count")
    public Integer imagesCount;
    @JsonProperty("datetime")
    public Integer datetime;
    @JsonProperty("in_gallery")
    public Boolean inGallery;
    @JsonProperty("in_most_viral")
    public Boolean inMostViral;
    @JsonProperty("tags")
    public Object tags;
    @JsonProperty("images")
    public Object images;
    @JsonProperty("has_sound")
    public Boolean hasSound;
    @JsonProperty("animated")
    public Boolean animated;
    @JsonProperty("type")
    public String type;
    @JsonProperty("size")
    public Integer size;

}
