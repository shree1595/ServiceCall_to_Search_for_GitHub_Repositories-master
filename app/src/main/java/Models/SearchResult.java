package Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {
    @SerializedName("total_count")
    int count;

    @SerializedName("items")
    List<SearchResultItem> items;

    public int getCount() {
        return count;
    }

    public List<SearchResultItem> getItems() {
        return items;
    }
}
