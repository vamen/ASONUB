package com.example.vivek.asonub.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vivek.asonub.ChatActvity;
import com.example.vivek.asonub.Constents.Constents;
import com.example.vivek.asonub.MainActivity;
import com.example.vivek.asonub.R;
import com.malinskiy.materialicons.IconDrawable;
import com.malinskiy.materialicons.Iconify;

/**
 * Created by Vivek Balachandran on 1/24/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    int sem;
    int branch;
    String sub[];
    public RecyclerAdapter(Context context) {
        super();
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        SharedPreferences s=context.getSharedPreferences(MainActivity.PREF_FILE_NAME,Context.MODE_PRIVATE);
        sem=s.getInt("sem",1);
        branch=s.getInt("branch",1);

        sub=Constents.sub[branch];

    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_card, parent, false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        holder.cardText.setText("subject");
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cardText;
        TextView chatIcon;

        public myViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cardText = (TextView) itemView.findViewById(R.id.cardText);
            chatIcon = (TextView) itemView.findViewById(R.id.iconchat);
            chatIcon.setCompoundDrawablesWithIntrinsicBounds(new IconDrawable(context, Iconify.IconValue.zmdi_comment).colorRes(android.R.color.black).sizeDp(20), null, null, null);
            chatIcon.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.card:
                    Log.d("onclick", "clicked @ card position" + getAdapterPosition() + "/");
                    break;
                case R.id.iconchat:
                    Intent goToChat = new Intent(context, ChatActvity.class);
                    goToChat.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(goToChat);
                    Log.d("onclick", "clicked chat @ position" + getAdapterPosition() + "/");

            }
        }
    }
}
