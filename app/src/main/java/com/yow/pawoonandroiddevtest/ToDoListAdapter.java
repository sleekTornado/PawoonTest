package com.yow.pawoonandroiddevtest;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rayhan on 15/03/18.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder> {
    private List<ToDoModel> mItems;
    private LayoutInflater inflater;


    public ToDoListAdapter(Context context, List<ToDoModel> items) {
        inflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public ToDoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.adapter_todo_list, parent, false);
        return new ToDoListViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ToDoListViewHolder holder, int position) {
        ToDoModel current = mItems.get(position);
        holder.toDo.setText(current.getTitle());
        if(current.isCompleted()){
            holder.done.setVisibility(View.VISIBLE);
        }else{
            holder.done.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ToDoListViewHolder extends  RecyclerView.ViewHolder{
        TextView toDo;
        ImageView done;

        public ToDoListViewHolder(View itemView, ToDoListAdapter toDoListAdapter) {
            super(itemView);
            toDo = (TextView) itemView.findViewById(R.id.todoId);
            done = (ImageView) itemView.findViewById(R.id.finishIcon);
        }
    }
}
