package com.ingsw.frontend.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.R;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder>{

    private ArrayList<User> userArrayList;
    public static int currentLayout = -1;

    public MemberAdapter(Context context, ArrayList<User> userArrayList){
        this.userArrayList = userArrayList;
    }


    @NonNull
    @Override
    public MemberAdapter.MemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View normalList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_nonclickable, parent,false);
        View selectionList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_selection, parent,false);

        if(getItemViewType(0) == -1)
            return new MemberAdapter.MemberHolder(normalList);
        else
            return  new MemberAdapter.MemberHolder(selectionList);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.MemberHolder holder, int position) {

        holder.textView.setText(userArrayList.get(position).getName());

        holder.checkBox.setChecked(false);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userArrayList.get(holder.getAdapterPosition()).setChecked(holder.checkBox.isChecked());
            }
        });


        if(userArrayList.get(holder.getAdapterPosition()).getChecked() == true)
            holder.checkBox.setChecked(true);
        else
            holder.checkBox.setChecked(false);

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    @Override
    public int getItemViewType(int position){

        return currentLayout;
    }

    public void setArrayList(ArrayList<User> arrayList) {
        this.userArrayList = arrayList;
    }

    public ArrayList<User> getArrayList(){
        return userArrayList;
    }

    public void clearList(){
        userArrayList.clear();
    }



    // ***************************************************************************************

    public class MemberHolder extends RecyclerView.ViewHolder{

        private CheckBox checkBox;
        private TextView textView;

        public MemberHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_cardview);
            checkBox = itemView.findViewById(R.id.checkbox_category);
        }
    }
}
