package com.anita.pricify.newsapp.newschannel.view;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.anita.pricify.newsapp.R;
import com.anita.pricify.newsapp.newschannel.model.NewsModel;
import com.anita.pricify.newsapp.webapi.WebUrl;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amitchipkar on 19/09/17.
 */

public class NewsAdaptor  extends RecyclerView.Adapter<NewsAdaptor.NewsViewHolder> {

    private List<NewsModel> newsModelList;
    private Context context;
    NewsModel newsModel;
    private ProgressDialog pDialog;
    private CardView mCardView;

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView mTextAuthor ,mTextTitle,mTextPublishedAt;
        NewsModel newsModel = null;

        public void setNewsModel(NewsModel newsModel) {
            this.newsModel = newsModel;
        }

        public NewsViewHolder(View view) {
            super(view);
            mTextAuthor = (TextView) view.findViewById(R.id.tv_author);
            mTextTitle = (TextView) view.findViewById(R.id.tv_title);
            mTextPublishedAt = (TextView) view.findViewById(R.id.tv_published_at);
            mCardView = (CardView) view.findViewById(R.id.card_view);

        }
    }

    public NewsAdaptor(Context context) {
        this.context = context;
        this.newsModelList = new ArrayList<>();
        getNewsListData(this);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_news_item, parent, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        newsModel = newsModelList.get(position);
        holder.mTextAuthor.setText(newsModel.getAuthor());
        holder.mTextTitle.setText(newsModel.getTitle());
        String strDate = newsModel.getPublishedAt();
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date dt = new Date();
        try {
            dt = sd1.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String newDate = sd2.format(dt);
        holder.mTextPublishedAt.setText(newDate);
        holder.setNewsModel(newsModel);
        holder.setIsRecyclable(false);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("news",  newsModelList.get(position));
                Intent intent = new Intent(context, NewsDetails.class);
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }

    public void getNewsListData(final NewsAdaptor newsAdaptor) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, WebUrl.WEB_NEWS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray newsArray = obj.getJSONArray("articles");
                            for (int i = 0; i < newsArray.length(); i++) {
                                JSONObject newsObject = newsArray.getJSONObject(i);
                                newsModel = new NewsModel(newsObject.getString("author"), newsObject.getString("title"),
                                        newsObject.getString("publishedAt"),newsObject.getString("description") ,
                                        newsObject.getString("url"),newsObject.getString("urlToImage"));
                                newsModelList.add(newsModel);
                                newsAdaptor.notifyDataSetChanged();
                                VolleyLog.d("Data", response);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error_network_timeout),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error_server),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error_network),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error_parse),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        stringRequest.setShouldCache(false);
        requestQueue.add(stringRequest);


    }
}

