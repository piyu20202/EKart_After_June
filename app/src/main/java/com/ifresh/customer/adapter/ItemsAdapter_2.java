package com.ifresh.customer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.ifresh.customer.R;
import com.ifresh.customer.activity.TrackerDetailActivity;
import com.ifresh.customer.helper.ApiConfig;
import com.ifresh.customer.helper.Constant;
import com.ifresh.customer.helper.Session;
import com.ifresh.customer.helper.VolleyCallback;
import com.ifresh.customer.model.OrderTracker_2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.ifresh.customer.activity.TrackerDetailActivity.pBar;

public class ItemsAdapter_2 extends RecyclerView.Adapter<ItemsAdapter_2.CartItemHolder> {

    Activity activity;
    ArrayList<OrderTracker_2> orderTrackerArrayList;
    String from = "";
    SpannableString spannableString;


    public ItemsAdapter_2(Activity activity, ArrayList<OrderTracker_2> orderTrackerArrayList) {
        this.activity = activity;
        this.orderTrackerArrayList = orderTrackerArrayList;

    }

    public ItemsAdapter_2(Activity activity, ArrayList<OrderTracker_2> orderTrackerArrayList, String from) {
        this.activity = activity;
        this.orderTrackerArrayList = orderTrackerArrayList;
        this.from = from;

    }

    @Override
    public CartItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_lyt, null);
        CartItemHolder cartItemHolder = new CartItemHolder(v);
        return cartItemHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final CartItemHolder holder, final int position) {

        final OrderTracker_2 order = orderTrackerArrayList.get(position);

        String payType = "";
        if (order.getPayment_method().equalsIgnoreCase("cod"))
            payType = activity.getResources().getString(R.string.cod);
        else
            payType = order.getPayment_method();
        String activeStatus = order.getActiveStatus();

        if(order.getOrder_type().equalsIgnoreCase("2"))
        {
            holder.txtqtxt.setVisibility(View.GONE);
            holder.txtqty.setVisibility(View.GONE);
        }
        else{

            holder.txtqtxt.setVisibility(View.VISIBLE);
            holder.txtqty.setVisibility(View.VISIBLE);

            if (!(order.getQuantity().equals(order.getRevised_qty()) || order.getRevised_qty().equalsIgnoreCase("")))
            {
                holder.txtRevisedQnt.setVisibility(View.VISIBLE);
                holder.txtRevisedQnt.setText(order.getRevised_qty());

                spannableString = new SpannableString(order.getQuantity());
                spannableString.setSpan(new StrikethroughSpan(), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.txtqty.setText(spannableString);

            }
            else
            {
                holder.txtqty.setText(order.getQuantity());
            }

        }

        if (order.getRevised_status().equals("0") || order.getRevised_status().equals("2"))
        {
            holder.mainLyt.setBackgroundColor(activity.getResources().getColor(R.color.light_gray));
            if (order.getRevised_status().equals("0"))
            {
                holder.txtRevisedStatus.setText("not available");
                holder.txtRevisedStatus.setTextColor(activity.getResources().getColor(R.color.red));
            }
            else if (order.getRevised_status().equals("2"))
            {
                holder.txtRevisedStatus.setText("return");
                holder.txtRevisedStatus.setTextColor(activity.getResources().getColor(R.color.red));
            }
        }

         if(order.getOrder_type().equalsIgnoreCase("2"))
         {
             holder.txtunit.setVisibility(View.GONE);
             holder.txtunit_1.setVisibility(View.GONE);
         }
         else{
             holder.txtunit.setVisibility(View.VISIBLE);
             holder.txtunit_1.setVisibility(View.VISIBLE);


             String unit_val="";
             if(order.getMeasurement().equalsIgnoreCase("kg(s)."))
                 unit_val = "1";
             else if(order.getMeasurement().equalsIgnoreCase("gms"))
                 unit_val = "2";
             else if(order.getMeasurement().equalsIgnoreCase("ltr"))
                 unit_val = "3";
             else if(order.getMeasurement().equalsIgnoreCase("ml"))
                 unit_val = "4";
             else if(order.getMeasurement().equalsIgnoreCase("pack"))
                 unit_val = "5";
             else if(order.getMeasurement().equalsIgnoreCase("pcs"))
                 unit_val = "6";
             else if(order.getMeasurement().equalsIgnoreCase("m"))
                 unit_val = "7";


             String revised_unit="";
             if(order.getRevised_unit().equalsIgnoreCase("1"))
                 revised_unit = "kg(s).";
             else if(order.getRevised_unit().equalsIgnoreCase("2"))
                 revised_unit = "gms";
             else if(order.getRevised_unit().equalsIgnoreCase("3"))
                 revised_unit = "ltr";
             else if(order.getRevised_unit().equalsIgnoreCase("4"))
                 revised_unit = "ml";
             else if(order.getRevised_unit().equalsIgnoreCase("5"))
                 revised_unit = "pack";
             else if(order.getRevised_unit().equalsIgnoreCase("6"))
                 revised_unit = "pcs";
             else if(order.getRevised_unit().equalsIgnoreCase("7"))
                 revised_unit = "m";






             Log.d("unit_val",unit_val);
             Log.d("revised_val",order.getRevised_unit());


             String mes_val = order.getUnit();
             Log.d("mes_val",mes_val);
             Log.d("revised_mes_val",order.getRevised_measurement());







             if (!(unit_val.equals(order.getRevised_unit()) || order.getRevised_unit().equalsIgnoreCase("")))
             {
                 if (!(mes_val.equals(order.getRevised_measurement()) || order.getMeasurement().equalsIgnoreCase("")))
                 {
                     holder.txtRevisedUnit.setVisibility(View.VISIBLE);
                     if(order.getRevised_measurement().equalsIgnoreCase("1"))
                     {
                         holder.txtRevisedUnit.setText(order.getRevised_measurement()+revised_unit);
                     }
                     else if(order.getRevised_measurement().equalsIgnoreCase("2"))
                     {
                         holder.txtRevisedUnit.setText(order.getRevised_measurement()+revised_unit);
                     }
                     else if(order.getRevised_measurement().equalsIgnoreCase("3"))
                     {
                         holder.txtRevisedUnit.setText(order.getRevised_measurement()+revised_unit);
                     }
                     else if(order.getRevised_measurement().equalsIgnoreCase("4"))
                     {
                         holder.txtRevisedUnit.setText(order.getRevised_measurement()+revised_unit);
                     }
                     else if(order.getRevised_measurement().equalsIgnoreCase("5"))
                     {
                         holder.txtRevisedUnit.setText(order.getRevised_measurement()+revised_unit);
                     }
                     else if(order.getRevised_measurement().equalsIgnoreCase("6"))
                     {
                         holder.txtRevisedUnit.setText(order.getRevised_measurement()+revised_unit);
                     }
                     else if(order.getRevised_measurement().equalsIgnoreCase("7"))
                     {
                         holder.txtRevisedUnit.setText(order.getRevised_measurement()+revised_unit);
                     }

                     //holder.txtRevisedUnit.setText(order.getRevised_unit()+" "+ order.getRevised_measurement());
                     spannableString = new SpannableString(order.getUnit()+" "+ order.getMeasurement());
                     spannableString.setSpan(new StrikethroughSpan(), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                     holder.txtunit_1.setText(spannableString);

                     holder.txtRevisedUnit.setText(order.getRevised_measurement()+revised_unit);
                 }



             }

             else
             {
                 holder.txtunit_1.setText(order.getUnit() + " " + order.getMeasurement());
             }

         }

        if(order.getOrder_type().equalsIgnoreCase("2"))
        {
            holder.txtprice.setVisibility(View.GONE);
        }
        else{

            Log.d("order_price",""+order.getPrice());
            Log.d("order_revised_price",""+(order.getRevised_price()+".0"));
            if (!(order.getPrice().equals((order.getRevised_price()+".0")) || order.getRevised_price().equalsIgnoreCase("")))
            {

                holder.txtRevisedPrice.setVisibility(View.VISIBLE);
                spannableString = new SpannableString(Constant.SETTING_CURRENCY_SYMBOL + order.getPrice());
                spannableString.setSpan(new StrikethroughSpan(), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.txtprice.setText(spannableString);

                holder.txtRevisedPrice.setText(Constant.SETTING_CURRENCY_SYMBOL+order.getRevised_price());
            }
            else
            {
                holder.txtprice.setVisibility(View.VISIBLE);
                holder.txtprice.setText(Constant.SETTING_CURRENCY_SYMBOL + order.getPrice());
            }
        }

        holder.txtpaytype.setText(activity.getResources().getString(R.string.via) + payType);
        holder.txtstatus.setText(activeStatus);

        if(order.getActiveStatus().equalsIgnoreCase("cancelled"))
        {
            holder.txtstatus.setTextColor(activity.getResources().getColor(R.color.red));
        }
        else if(order.getActiveStatus().equalsIgnoreCase("received"))
        {
            holder.txtstatus.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
        }
        else if(order.getActiveStatus().equalsIgnoreCase("delivered"))
        {
            holder.txtstatus.setTextColor(activity.getResources().getColor(R.color.orange));
        }
        holder.txtstatusdate.setText(order.getActiveStatusDate());

        if(order.getName().equalsIgnoreCase("null"))
        {
           if(order.getOrder_type().equalsIgnoreCase("2"))
           {
               holder.txtname.setText("Medical Prescription");
           }
           else{
               holder.txtname.setText("iFresh Product");
           }
        }
        else{
            holder.txtname.setText(order.getName());
        }


        holder.imgorder.setDefaultImageResId(R.drawable.placeholder);
        holder.imgorder.setErrorImageResId(R.drawable.placeholder);
        holder.imgorder.setImageUrl(order.getImage(), Constant.imageLoader);

        holder.carddetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, TrackerDetailActivity.class).putExtra("model", order));
            }
        });


        if (from.equals("detail")) {

            if (order.getActiveStatus().equalsIgnoreCase("cancelled")) {
                holder.txtstatus.setTextColor(Color.RED);
                holder.btnCancel.setVisibility(View.GONE);
            } else if (order.getActiveStatus().equalsIgnoreCase("delivered")) {
                holder.btnCancel.setVisibility(View.GONE);
                holder.btnReturn.setVisibility(View.VISIBLE);
            } else if (order.getActiveStatus().equalsIgnoreCase("returned")) {
                holder.btnCancel.setVisibility(View.GONE);
                holder.btnReturn.setVisibility(View.GONE);
            } else {
                holder.btnCancel.setVisibility(View.VISIBLE);
            }

            holder.lyttracker.setVisibility(View.VISIBLE);

            if (order.getActiveStatus().equalsIgnoreCase("cancelled")) {
                holder.lyttracker.setVisibility(View.GONE);
            } else {
                if (order.getActiveStatus().equals("returned")) {
                    holder.l4.setVisibility(View.VISIBLE);
                    holder.returnLyt.setVisibility(View.VISIBLE);
                }
                holder.lyttracker.setVisibility(View.VISIBLE);

                ApiConfig.setOrderTrackerLayout_2(activity, order, holder);
            }
        }
    }

    private void updateOrderStatus(final Activity activity, final OrderTracker_2 order, final String status, final CartItemHolder holder, final String from) {

        final Map<String, String> params = new HashMap<>();
        params.put(Constant.UPDATE_ORDER_ITEM_STATUS, Constant.GetVal);
        params.put(Constant.ORDER_ITEM_ID, order.getId());
        params.put(Constant.ORDER_ID, order.getOrder_id());
        params.put(Constant.STATUS, status);


        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        // Setting Dialog Message
        if (status.equals(Constant.CANCELLED)) {
            alertDialog.setTitle(activity.getResources().getString(R.string.cancel_order));
            alertDialog.setMessage(activity.getResources().getString(R.string.cancel_msg));
        } else if (status.equals(Constant.RETURNED)) {
            alertDialog.setTitle(activity.getResources().getString(R.string.return_order));
            alertDialog.setMessage(activity.getResources().getString(R.string.return_msg));
        }
        alertDialog.setCancelable(false);
        final AlertDialog alertDialog1 = alertDialog.create();

        // Setting OK Button
        alertDialog.setPositiveButton(activity.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (pBar != null)
                    pBar.setVisibility(View.VISIBLE);
                ApiConfig.RequestToVolley(new VolleyCallback() {
                    @Override
                    public void onSuccess(boolean result, String response) {
                        // System.out.println("================= " + response);
                        if (result) {
                            try {
                                JSONObject object = new JSONObject(response);
                                if (!object.getBoolean(Constant.ERROR)) {
                                    if (status.equals(Constant.CANCELLED)) {
                                        holder.btnCancel.setVisibility(View.GONE);
                                        holder.txtstatus.setText(status);
                                        holder.txtstatus.setTextColor(Color.RED);
                                        holder.lyttracker.setVisibility(View.GONE);
                                        order.status = status;
                                        if (from.equals("detail")) {
                                            if (orderTrackerArrayList.size() == 1) {
                                                TrackerDetailActivity.btnCancel.setVisibility(View.GONE);
                                                TrackerDetailActivity.lyttracker.setVisibility(View.GONE);
                                            }
                                        }
                                        ApiConfig.getWalletBalance(activity, new Session(activity));
                                    } else {
                                        holder.btnReturn.setVisibility(View.GONE);
                                        holder.txtstatus.setText(status);
                                    }
                                    Constant.isOrderCancelled = true;
                                }
                                Toast.makeText(activity, object.getString("message"), Toast.LENGTH_LONG).show();
                                if (pBar != null)
                                    pBar.setVisibility(View.GONE);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, activity, Constant.ORDERPROCESS_URL, params, false);

            }
        });
        alertDialog.setNegativeButton(activity.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog1.dismiss();
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }

    public class CartItemHolder extends RecyclerView.ViewHolder {
        TextView txtqty, txtqtxt,txtprice, txtpaytype, txtstatus, txtstatusdate, txtname,txtunit_1,txtunit, txtRevisedPrice, txtRevisedQnt, txtRevisedUnit, txtRevisedStatus;
        NetworkImageView imgorder;
        CardView carddetail;
        RecyclerView recyclerView;
        Button btnCancel, btnReturn;
        View l4;
        LinearLayout lyttracker, returnLyt;
        RelativeLayout mainLyt;

        public CartItemHolder(View itemView) {
            super(itemView);
            txtqtxt = itemView.findViewById(R.id.txtqtxt);
            txtqty = itemView.findViewById(R.id.txtqty);
            txtunit_1 = itemView.findViewById(R.id.txtunit_1);
            txtunit = itemView.findViewById(R.id.txtunit);
            txtprice = itemView.findViewById(R.id.txtprice);
            txtpaytype = itemView.findViewById(R.id.txtpaytype);
            txtstatus = itemView.findViewById(R.id.txtstatus);
            txtstatusdate = itemView.findViewById(R.id.txtstatusdate);
            txtname = itemView.findViewById(R.id.txtname);
            btnCancel = itemView.findViewById(R.id.btnCancel);
            imgorder = itemView.findViewById(R.id.imgorder);
            carddetail = itemView.findViewById(R.id.carddetail);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            btnReturn = itemView.findViewById(R.id.btnReturn);
            lyttracker = itemView.findViewById(R.id.lyttracker);
            l4 = itemView.findViewById(R.id.l4);
            returnLyt = itemView.findViewById(R.id.returnLyt);
            txtRevisedPrice = itemView.findViewById(R.id.txtRevisedName);
            txtRevisedQnt = itemView.findViewById(R.id.txtRevisedQnt);
            mainLyt = itemView.findViewById(R.id.itemsLyt);
            txtRevisedUnit = itemView.findViewById(R.id.txtRevisedUnit);
            txtRevisedStatus = itemView.findViewById(R.id.txtRevisedStatus);
        }
    }


    @Override
    public int getItemCount() {
        return orderTrackerArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
