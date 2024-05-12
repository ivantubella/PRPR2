package edu.url.salle.ivan.tubella.proyectofinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
    private ArrayList<String> lItems;
    private Activity activity;

    public ItemAdapter(ArrayList<String> lItems, Activity activity) {
        this.lItems = lItems;
        this.activity = activity;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new ItemHolder(layoutInflater, parent, activity);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        String item = lItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return lItems.size();
    }
}
