package id.duakoma.com.meetsquare.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.duakoma.com.meetsquare.R;
import id.duakoma.com.meetsquare.RoomActivity;

/**
 * Created by lenovo on 9/10/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    private Context context;
    private List<MyData> myData;
    private id.duakoma.com.meetsquare.adapter.CustomAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(id.duakoma.com.meetsquare.adapter.CustomAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public CustomAdapter(Context context, List<MyData> myData){
        this.context = context;
        this.myData = myData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_room,parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.description.setText(myData.get(position).getDescription());
        holder.title.setText(myData.get(position).getRoom_name());
        Glide.with(context).load(myData.get(position).getImage_link()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView description, title;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            description = (TextView)itemView.findViewById(R.id.card_view_image_subtitle);
            title = (TextView)itemView.findViewById(R.id.card_view_image_title);
            imageView = (ImageView) itemView.findViewById(R.id.card_view_image);
            int maxLength = 100;
            InputFilter[] fArray = new InputFilter[1];
            fArray[0] = new InputFilter.LengthFilter(maxLength);
            description.setFilters(fArray);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!= null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
