package com.example.vivek.asonub.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vivek.asonub.R;
import com.malinskiy.materialicons.IconDrawable;
import com.malinskiy.materialicons.Iconify;

import java.util.zip.Inflater;

/**
 * Created by Vivek Balachandran on 1/24/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder>{
      Context context;
        LayoutInflater layoutInflater;
    public RecyclerAdapter(Context context) {
        super();
        layoutInflater=LayoutInflater.from(context);
        this.context=context;

    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_card,parent,false);
        myViewHolder holder=new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position)  {

        holder.cardText.setText("subject");
    }
    @Override
    public int getItemCount() {
        return 6;
    }
    class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView cardText;
        TextView chatIcon;
        public myViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cardText= (TextView) itemView.findViewById(R.id.cardText);
            chatIcon= (TextView) itemView.findViewById(R.id.iconchat);
            chatIcon.setCompoundDrawablesWithIntrinsicBounds(new IconDrawable(context, Iconify.IconValue.zmdi_comment).colorRes(android.R.color.black).sizeDp(20), null, null, null);
            chatIcon.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case  R.id.card :
                    Log.d("onclick","clicked @ card position"+getAdapterPosition()+"/");
                    break;
                case R.id.iconchat :
                    Log.d("onclick","clicked chat @ position"+getAdapterPosition()+"/");

            }
        }
    }
}
