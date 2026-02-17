package com.example.onlinemovieticket.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlinemovieticket.R;
import com.example.onlinemovieticket.models.Seat;
import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {
    private List<Seat> seatList;
    private OnSeatClickListener listener;

    public interface OnSeatClickListener {
        void onSeatClick(Seat seat);
    }

    public SeatAdapter(List<Seat> seatList, OnSeatClickListener listener) {
        this.seatList = seatList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat, parent, false);
        return new SeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        Seat seat = seatList.get(position);
        holder.bind(seat);
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }

    class SeatViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSeatNumber;

        public SeatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSeatNumber = itemView.findViewById(R.id.tvSeatNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onSeatClick(seatList.get(position));
                    }
                }
            });
        }

        public void bind(Seat seat) {
            tvSeatNumber.setText(seat.getSeatNumber());
            
            if (seat.isBooked()) {
                tvSeatNumber.setBackgroundColor(Color.RED);
                tvSeatNumber.setTextColor(Color.WHITE);
            } else if (seat.isSelected()) {
                tvSeatNumber.setBackgroundColor(Color.GREEN);
                tvSeatNumber.setTextColor(Color.WHITE);
            } else {
                tvSeatNumber.setBackgroundColor(Color.LTGRAY);
                tvSeatNumber.setTextColor(Color.BLACK);
            }
        }
    }
}