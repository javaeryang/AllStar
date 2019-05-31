package com.wesuper.allstar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wesuper.allstar.R;
import com.wesuper.allstar.entity.Card;

import java.util.List;

/**
 * Created by Administrator on 2019/5/31.
 */

public class CardAdapter extends BaseAdapter{

    private Context context;

    public CardAdapter(Context context) {
        this.context = context;
    }

    private List<Card> cards;

    public void setData(List<Card> cards){
        this.cards = cards;
    }

    public List<Card> getData(){
        return cards;
    }

    @Override
    public int getCount() {
        return cards == null ? 0 : cards.size();
    }

    @Override
    public Object getItem(int position) {
        return cards == null ? null : cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardHolder holder;
        View view;
        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);

            view = layoutInflater.inflate(R.layout.card_item, null);

            TextView title = view.findViewById(R.id.card_title);
            TextView version = view.findViewById(R.id.version);
            TextView textView = view.findViewById(R.id.card_text);

            holder = new CardHolder();
            holder.title = title;
            holder.version = version;
            holder.text = textView;

            view.setTag(holder);
        }else {
            view = convertView;
            holder = (CardHolder) convertView.getTag();
        }
        Card card = getData().get(position);
        holder.title.setText(card.getTitle());
        holder.version.setText(card.getVersion());
        holder.text.setText(card.getText());

        return view;
    }

    public class CardHolder{
        TextView title;
        TextView version;
        TextView text;
    }
}
