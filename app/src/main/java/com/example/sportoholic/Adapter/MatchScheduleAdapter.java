package com.example.sportoholic.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportoholic.R;
import com.example.sportoholic.RetrofitApi.Schedule.ClsSchedule;
import com.example.sportoholic.RetrofitApi.Schedule.ClsScheduleMatch;
import com.example.sportoholic.RetrofitApi.Schedule.ClsScheduleVenue;
import com.example.sportoholic.databinding.ItemMatchScheduleBinding;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchScheduleAdapter extends RecyclerView.Adapter<MatchScheduleAdapter.ViewHolder> {

    private List<ClsScheduleMatch> list = new ArrayList<>();
    private Context context;

    public MatchScheduleAdapter(Context mContext) {
        this.context = mContext;
    }

    public void addItems(List<ClsScheduleMatch> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("--abc--", "onCreateViewHolder: ");
        Context context = parent.getContext();
        ItemMatchScheduleBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.item_match_schedule, parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClsScheduleMatch current = list.get(position);

        ClsScheduleVenue venue = current.getClsScheduleVenue();

        long startTime = Long.parseLong(current.getStartTime());
        Date date = new java.util.Date(startTime*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy / HH:mm");
        String formattedDate = sdf.format(date);

        holder.binding.tvTitle.setText(current.getMDesc());
        holder.binding.tvTime.setText(formattedDate);
        holder.binding.tvVenue.setText(venue.getCountry()+" - "+venue.getCity());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemMatchScheduleBinding binding;

        ViewHolder(@NonNull ItemMatchScheduleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
