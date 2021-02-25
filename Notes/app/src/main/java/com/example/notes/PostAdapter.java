package com.example.notes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> implements Filterable {
    private Context context;
    private ArrayList<Post> listPost;
    private ArrayList<Post> mArrayList;

    private DBHelper mDatabase;

    public PostAdapter(Context context, ArrayList<Post> listPost) {
        this.context = context;
        this.listPost = listPost;
        this.mArrayList= listPost;
        mDatabase = new DBHelper(context);
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        final Post Post = listPost.get(position);

        holder.name.setText(Post.getName());
        holder.desc.setText(Post.getDesc());
        holder.tanggal.setText(Post.getDate());

        holder.editPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(Post);
            }
        });

        holder.deletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database

                mDatabase.deleteContact(Post.getId());

                //refresh the activity page.
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    listPost = mArrayList;
                } else {

                    ArrayList<Post> filteredList = new ArrayList<>();

                    for (Post Post : mArrayList) {

                        if (Post.getName().toLowerCase().contains(charString)) {

                            filteredList.add(Post);
                        }
                    }

                    listPost = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listPost;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listPost = (ArrayList<Post>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    @Override
    public int getItemCount() {
        return listPost.size();
    }


    private void editTaskDialog(final Post Post){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.activity_form, null);

        final EditText nameField = (EditText)subView.findViewById(R.id.enter_name);
        final EditText descField = (EditText)subView.findViewById(R.id.enter_desc);

        if(Post != null){
            nameField.setText(Post.getName());
            descField.setText(String.valueOf(Post.getDesc()));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Catatan");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                final String desc = descField.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(context, "Terjadi kesalahan. Mohon Periksa Kembali Data ", Toast.LENGTH_LONG).show();
                }
                else{
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    Date date = new Date();
                    final String tanggal = dateFormat.format(date);
                    mDatabase.updatePost(new Post(Post.getId(), name, desc,tanggal));
                    //refresh the activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.show();
    }
}
