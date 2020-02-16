package com.example.myhub.databinding;

import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.myhub.R;
import com.example.myhub.util.StringCheckUtil;

import java.util.Date;

import androidx.core.text.HtmlCompat;
import androidx.databinding.BindingAdapter;

/**
 * Time:2020/2/14 11:21
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class HubBindingAdapter {

    @BindingAdapter(value = {"imgUrlLoad"}, requireAll = false)
    public static void imgUrlLoad(ImageView view, String imgUrl) {
        if (!StringCheckUtil.isNullString(imgUrl)) {
            Glide.with(view.getContext())
                    .load(imgUrl)
                    .placeholder(R.drawable.ic_avatar_default)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view);
        }
    }

    @BindingAdapter(value = {"bindText"}, requireAll = false)
    public static void bindText(TextView view, String content) {
        if (!StringCheckUtil.isNullString(content)) {
            view.setText(content);
        }
    }

    //detail_fragment
    @BindingAdapter(value = {"renderHtml"}, requireAll = false)
    public static void bindRenderHtml(TextView textView, String description) {
        if (description != null) {
            textView.setText(HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            textView.setText("");
        }
    }

    @BindingAdapter(value = {"setAgoTime"}, requireAll = false)
    public static void setAgoTime(TextView tv, Date content) {
        if (!(content == null)) {
            tv.setText(StringCheckUtil.getNewsTimeStr(tv.getContext(), content));
        }
    }

}
