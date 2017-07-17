package com.example.interviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.interviewdemo.R;
import com.example.interviewdemo.bean.CommentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Js on 2016/4/4.
 */
public class CommentListAdapter extends BaseAdapter {

    private Context context;
    private List<CommentBean.DataBean> list;

    public CommentListAdapter(Context mContext,List<CommentBean.DataBean> mList) {
        this.context = mContext;
        this.list = mList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_comment_item,null);
            holder = new ViewHolder();
            holder.portrait = (ImageView) convertView.findViewById(R.id.iv_portrait);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.starlevel = (RatingBar) convertView.findViewById(R.id.ratingBar1);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.date = (TextView) convertView.findViewById(R.id.comment_tv_date);
            holder.content = (TextView) convertView.findViewById(R.id.comment_tv_content);
            holder.commentImageOne = (ImageView) convertView.findViewById(R.id.comment_iv_one);
            holder.commentImageTwo = (ImageView) convertView.findViewById(R.id.comment_iv_two);
            holder.commentImageThree = (ImageView) convertView.findViewById(R.id.comment_iv_three);
            holder.commentImageFour = (ImageView) convertView.findViewById(R.id.comment_iv_four);
            holder.commentImageFive = (ImageView) convertView.findViewById(R.id.comment_iv_five);
            holder.view = convertView.findViewById(R.id.comment_view);
            holder.ll = (LinearLayout) convertView.findViewById(R.id.comment_ll);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        CommentBean.DataBean dataBean = list.get(position);
        String portrait = dataBean.getPortrait();
        Glide.with(context).load(portrait).into(holder.portrait);
        holder.name.setText(dataBean.getName());
        holder.title.setText(dataBean.getTitle());
        holder.starlevel.setRating(dataBean.getStarlevel());
        holder.date.setText(dataBean.getDate());
        holder.content.setText(dataBean.getConten());
        List<ImageView> image = new ArrayList<ImageView>();
        image.add(holder.commentImageOne);
        image.add(holder.commentImageTwo);
        image.add(holder.commentImageThree);
        image.add(holder.commentImageFour);
        image.add(holder.commentImageFive);
        List<String> commentimage = dataBean.getCommentimage();
        if (commentimage.size()> 0){
        for (int i= 0;i<commentimage.size();i++){
            Glide.with(context).load(commentimage.get(i)).into(image.get(i));
        }
        }else{
            holder.view.setVisibility(View.GONE);
            holder.ll.setVisibility(View.GONE);
        }
        return convertView;
    }

    private static class ViewHolder{
        ImageView  portrait,commentImageOne,commentImageTwo,commentImageThree,commentImageFour,commentImageFive;
        TextView name,title,content,date;
        RatingBar starlevel;
        View view;
        LinearLayout ll;
    }
}
