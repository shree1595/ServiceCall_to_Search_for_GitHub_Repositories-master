package Models;

import com.google.gson.annotations.SerializedName;

public class SearchResultItem {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("full_name")
    String full_name;

    @SerializedName("description")
    String description;

    @SerializedName("owner")
    SearchResultItemOwner owner;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getDescription() {
        return description;
    }

    public SearchResultItemOwner getOwner() {
        return owner;
    }
}
