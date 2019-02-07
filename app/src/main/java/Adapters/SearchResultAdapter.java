package Adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.codingchallenge.R;

import java.util.List;

import Models.SearchResultItem;

public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<SearchResultItem> searchResultItemList;
    public SearchResultAdapter(List<SearchResultItem> searchResultItemList) {
        this.searchResultItemList = searchResultItemList;
    }

    @Override
    public int getItemCount() {
        return searchResultItemList.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item_row, null);

        return new ResultViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ResultViewHolder) {
            ((ResultViewHolder) holder).populateData(searchResultItemList.get(position));
        }
    }

    class ResultViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nameTextView;
        private TextView descTextView;
        private ViewGroup viewGroup;

         ResultViewHolder(ViewGroup parent) {
            super(parent);

            this.viewGroup = parent;
            imageView = (ImageView) parent.findViewById(R.id.owner_image);
            nameTextView = (TextView) parent.findViewById(R.id.name);
            descTextView = (TextView) parent.findViewById(R.id.desc);
        }

        void populateData(SearchResultItem resultItem) {
             nameTextView.setText(resultItem.getFull_name());
             descTextView.setText(resultItem.getDescription());
             Glide.with(viewGroup.getContext())
                    .load(Uri.parse(resultItem.getOwner().getImageUrl()))
                     .override(100, 100)
                     .centerCrop()
                     .into(imageView);
        }


    }
}
