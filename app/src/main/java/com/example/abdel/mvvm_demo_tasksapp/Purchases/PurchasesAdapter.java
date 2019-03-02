package com.example.abdel.mvvm_demo_tasksapp.Purchases;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdel.mvvm_demo_tasksapp.R;

import java.util.List;

public class PurchasesAdapter extends RecyclerView.Adapter<PurchasesAdapter.PurchasesViewHolder> {

    private List<Purchase> purchasesList;

    public void setPurchasesList(List<Purchase> purchasesList) {
        this.purchasesList = purchasesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PurchasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PurchasesViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.purchases_single_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasesViewHolder holder, int position) {
        Purchase currentPurchase = purchasesList.get(position);
        holder.bind(
                currentPurchase.getTitle(),
                currentPurchase.getPrice(),
                currentPurchase.getCurrency()
        );
    }

    @Override
    public int getItemCount() {
        if(purchasesList == null)
            return 0;
        return purchasesList.size();
    }

    protected class PurchasesViewHolder extends RecyclerView.ViewHolder
    {
        TextView titleTextView, priceTextView, currencyTextView;
        public PurchasesViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.purchase_title_textView);
            priceTextView = itemView.findViewById(R.id.purchase_price_textView);
            currencyTextView = itemView.findViewById(R.id.purchase_currency_textView);
        }

        void bind(String title, int price, String currency)
        {
            titleTextView.setText(title);
            priceTextView.setText(Integer.toString(price));
            currencyTextView.setText(currency);
        }
    }
}
