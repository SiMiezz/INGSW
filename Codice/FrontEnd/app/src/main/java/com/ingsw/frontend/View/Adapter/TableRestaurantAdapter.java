package com.ingsw.frontend.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.TableRestaurant;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Fragment.TablesAllFragment;
import com.ingsw.frontend.View.Fragment.TablesSelectedFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TableRestaurantAdapter extends RecyclerView.Adapter<TableRestaurantAdapter.TableRestaurantHolder> {

    private static int tableNumber = 0;
    public ArrayList<TableRestaurant> tableRestaurantArrayList;
    private TablesSelectedFragment tablesSelectedFragment;

    public TableRestaurantAdapter(Context context, ArrayList<TableRestaurant> tableRestaurantArrayList, TablesSelectedFragment tablesSelectedFragment){
        this.tableRestaurantArrayList = tableRestaurantArrayList;
        this.tablesSelectedFragment = tablesSelectedFragment;
    }

    public ArrayList<TableRestaurant> getTableRestaurantArrayList() {
        return tableRestaurantArrayList;
    }

    public void setTableRestaurantArrayList(ArrayList<TableRestaurant> tableRestaurantArrayList) {
        this.tableRestaurantArrayList = tableRestaurantArrayList;
    }

    public void clearList(){
        tableRestaurantArrayList.clear();
    }

    // ***************************************************************************************

    @NonNull
    @Override
    public TableRestaurantAdapter.TableRestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new TableRestaurantAdapter.TableRestaurantHolder(LayoutInflater
                                                                .from(parent.getContext())
                                                                .inflate(R.layout.row_table_clickable, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TableRestaurantAdapter.TableRestaurantHolder holder, int position) {

        if(tableRestaurantArrayList.get(position).isFree()){
            holder.freeOccupiedTextView.setText("Free");
            switch (tableRestaurantArrayList.get(position).getSeats()){
                case 2:
                    holder.imageView.setImageResource(R.drawable.table_2_free);
                    break;
                case 4:
                    holder.imageView.setImageResource(R.drawable.table_4_free);
                    break;
                case 6:
                    holder.imageView.setImageResource(R.drawable.table_6_free);
                    break;
                case 8:
                    holder.imageView.setImageResource(R.drawable.table_8_free);
                    break;
                case 10:
                    holder.imageView.setImageResource(R.drawable.table_10_free);
                    break;
            }
        }
        else{
            holder.freeOccupiedTextView.setText("Occupied");
            switch (tableRestaurantArrayList.get(position).getSeats()){
                case 2:
                    holder.imageView.setImageResource(R.drawable.table_2_occupied);
                    break;
                case 4:
                    holder.imageView.setImageResource(R.drawable.table_4_occupied);
                    break;
                case 6:
                    holder.imageView.setImageResource(R.drawable.table_6_occupied);
                    break;
                case 8:
                    holder.imageView.setImageResource(R.drawable.table_8_occupied);
                    break;
                case 10:
                    holder.imageView.setImageResource(R.drawable.table_10_occupied);
                    break;
            }
        }


        holder.textView.setText(String.valueOf(holder.getAdapterPosition() + 1));

        TableRestaurant temp = tableRestaurantArrayList.get(holder.getAdapterPosition());

        if(holder.cardView != null)
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tablesSelectedFragment.getInfoTableFromClick(temp.getId());
                }
            });


    }

    @Override
    public int getItemCount() {
        return tableRestaurantArrayList.size();
    }




    // ***************************************************************************************
    // ***************************************************************************************
    // ***************************************************************************************

    public class TableRestaurantHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private ImageView imageView;
        private TextView textView;
        private TextView freeOccupiedTextView;

        public TableRestaurantHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.row_clickable_table);
            imageView = itemView.findViewById(R.id.table_image);
            textView = itemView.findViewById(R.id.table_number);
            freeOccupiedTextView = itemView.findViewById(R.id.free_occupied_text);
        }
    }

}
