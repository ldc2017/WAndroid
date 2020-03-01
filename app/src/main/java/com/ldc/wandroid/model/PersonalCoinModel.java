package com.ldc.wandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonalCoinModel {
    /**
     * curPage : 1
     * datas : [{"coinCount":39,"date":1582992935000,"desc":"2020-03-01 00:15:35 签到 , 积分：10 + 29","id":151988,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":38,"date":1582905630000,"desc":"2020-02-29 00:00:30 签到 , 积分：10 + 28","id":151540,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":37,"date":1582819268000,"desc":"2020-02-28 00:01:08 签到 , 积分：10 + 27","id":151009,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":36,"date":1582733380000,"desc":"2020-02-27 00:09:40 签到 , 积分：10 + 26","id":150490,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":35,"date":1582649688000,"desc":"2020-02-26 00:54:48 签到 , 积分：10 + 25","id":149977,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":34,"date":1582602511000,"desc":"2020-02-25 11:48:31 签到 , 积分：10 + 24","id":149710,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":33,"date":1581759817000,"desc":"2020-02-15 17:43:37 签到 , 积分：10 + 23","id":144612,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":32,"date":1579335223000,"desc":"2020-01-18 16:13:43 签到 , 积分：10 + 22","id":135361,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":31,"date":1579268895000,"desc":"2020-01-17 21:48:15 签到 , 积分：10 + 21","id":135050,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":30,"date":1577621210000,"desc":"2019-12-29 20:06:50 签到 , 积分：10 + 20","id":122825,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":29,"date":1577509484000,"desc":"2019-12-28 13:04:44 签到 , 积分：10 + 19","id":122413,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":28,"date":1577462336000,"desc":"2019-12-27 23:58:56 签到 , 积分：10 + 18","id":122197,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":27,"date":1577204957000,"desc":"2019-12-25 00:29:17 签到 , 积分：10 + 17","id":120142,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":26,"date":1576341169000,"desc":"2019-12-15 00:32:49 签到 , 积分：10 + 16","id":113981,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":25,"date":1576256050000,"desc":"2019-12-14 00:54:10 签到 , 积分：10 + 15","id":113637,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":24,"date":1576252377000,"desc":"2019-12-13 23:52:57 签到 , 积分：10 + 14","id":113606,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":23,"date":1575786822000,"desc":"2019-12-08 14:33:42 签到 , 积分：10 + 13","id":109843,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":22,"date":1575720920000,"desc":"2019-12-07 20:15:20 签到 , 积分：10 + 12","id":109612,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":21,"date":1575555953000,"desc":"2019-12-05 22:25:53 签到 , 积分：10 + 11","id":108411,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"},{"coinCount":20,"date":1575185131000,"desc":"2019-12-01 15:25:31 签到 , 积分：10 + 10","id":105022,"reason":"签到","type":1,"userId":26308,"userName":"ldc_2019"}]
     * offset : 0
     * over : false
     * pageCount : 2
     * size : 20
     * total : 30
     */

    @SerializedName("curPage")
    private int curPage;
    @SerializedName("offset")
    private int offset;
    @SerializedName("over")
    private boolean over;
    @SerializedName("pageCount")
    private int pageCount;
    @SerializedName("size")
    private int size;
    @SerializedName("total")
    private int total;
    @SerializedName("datas")
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * coinCount : 39
         * date : 1582992935000
         * desc : 2020-03-01 00:15:35 签到 , 积分：10 + 29
         * id : 151988
         * reason : 签到
         * type : 1
         * userId : 26308
         * userName : ldc_2019
         */

        @SerializedName("coinCount")
        private int coinCount;
        @SerializedName("date")
        private long date;
        @SerializedName("desc")
        private String desc;
        @SerializedName("id")
        private int id;
        @SerializedName("reason")
        private String reason;
        @SerializedName("type")
        private int type;
        @SerializedName("userId")
        private int userId;
        @SerializedName("userName")
        private String userName;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
