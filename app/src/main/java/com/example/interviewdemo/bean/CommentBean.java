package com.example.interviewdemo.bean;

import java.util.List;

/**
 * Created by Js on 2016/4/3.
 */
public class CommentBean {

    /**
     * portrait : null
     * name : 我是布偶我怕谁
     * starlevel : 3
     * title : 很好吃很好吃
     * date : 2016-03-31 01:26
     * conten : 这里的车厘子呈暗红色,果实硕大,坚实而多汁,入口甜美.每年,美国的车厘子只有两个月的成熟时间
     * commentimage : []
     */

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String portrait;
        private String name;
        private int starlevel;
        private String title;
        private String date;
        private String conten;
        private List<String> commentimage;

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStarlevel() {
            return starlevel;
        }

        public void setStarlevel(int starlevel) {
            this.starlevel = starlevel;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getConten() {
            return conten;
        }

        public void setConten(String conten) {
            this.conten = conten;
        }

        public List<String> getCommentimage() {
            return commentimage;
        }

        public void setCommentimage(List<String> commentimage) {
            this.commentimage = commentimage;
        }
    }
}
