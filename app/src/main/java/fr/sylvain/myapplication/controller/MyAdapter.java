package fr.sylvain.myapplication.controller;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.sylvain.myapplication.R;
import fr.sylvain.myapplication.model.Movie;
import fr.sylvain.myapplication.view.MovieFrag;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final MovieFrag ma;
    private List<Movie> values;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtName;
        public ImageView imgMovie;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtName = (TextView) v.findViewById(R.id.cell_movie_name);
            imgMovie = (ImageView) v.findViewById(R.id.cell_image_movie);
        }
    }

    public void add(int position, Movie item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Movie> myDataset, MovieFrag ma, Context context) {
        values = myDataset;
        this.ma = ma;
        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Movie selectedMovie = values.get(position);
        final String name = selectedMovie.getTitle();
        holder.txtName.setText(name);
        holder.txtName.setTag(selectedMovie.getId());
        holder.txtName.setOnClickListener(ma);
        Picasso.with(context).load(selectedMovie.getImage()).into(holder.imgMovie);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return values == null ? 0 : values.size();
        //return values.size();

    }

}