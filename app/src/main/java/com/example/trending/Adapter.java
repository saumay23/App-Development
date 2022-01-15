package com.example.trending;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{


    List<ModelClass> UserList;
    public  Adapter (List<ModelClass> UserList)
    {
        this.UserList=UserList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        holder.Name.setText(UserList.get(position).getName());
        holder.desc.setText(UserList.get(position).getDesc());
        holder.forkcount.setText(UserList.get(position).getForkcount());
        holder.starcount.setText(UserList.get(position).getStarcount());
        holder.language.setText(UserList.get(position).getLanguage());
        holder.content.setText(UserList.get(position).getContent());
        String color=UserList.get(position).getLangcolor();
        if (color.equalsIgnoreCase("null"))
        {
        }
        else
        {
            holder.langcolor.setBackgroundColor(Color.parseColor(UserList.get(position).getLangcolor()));
        }
        boolean isexpanded =UserList.get(position).isExpanded();
        holder.expandable_layout.setVisibility(isexpanded? View.VISIBLE : View.GONE);
        Picasso.get().load(UserList.get(position).getDisplay_pic()).fit().into(holder.displaypic);
    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView Name,content,starcount,forkcount,language,langcolor;
        TextView desc;
        ImageView displaypic;
        RelativeLayout expandable_layout, expander;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            Name = itemView.findViewById((R.id.name));
            desc= itemView.findViewById(R.id.description);
            displaypic =itemView.findViewById(R.id.dp);
            langcolor=itemView.findViewById(R.id.langcolor);
            content=itemView.findViewById(R.id.content);
            starcount=itemView.findViewById(R.id.starcount);
            forkcount=itemView.findViewById(R.id.forkcount);
            language=itemView.findViewById(R.id.langtext);
            expandable_layout=itemView.findViewById(R.id.collapsable);
            expander=itemView.findViewById(R.id.fixed);
            expander.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ModelClass userlist=UserList.get(getAdapterPosition());
                    userlist.setExpanded(!userlist.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }


    }
}
