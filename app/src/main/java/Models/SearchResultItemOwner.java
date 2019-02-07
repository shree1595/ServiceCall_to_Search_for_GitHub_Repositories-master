package Models;

import com.google.gson.annotations.SerializedName;

public class SearchResultItemOwner {
    @SerializedName("avatar_url")
    String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }
}
