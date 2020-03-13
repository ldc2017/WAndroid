package com.ldc.wandroid.model;

import java.util.List;

public class PrivateSharedArticleModel {
    /**
     * coinInfo : {"coinCount":900,"level":9,"rank":528,"userId":26308,"username":"l**_2019"}
     * shareArticles : {"curPage":1,"datas":[],"offset":0,"over":true,"pageCount":0,"size":20,"total":0}
     */

    private CoinInfoBean coinInfo;
    private ShareArticlesBean shareArticles;

    public CoinInfoBean getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfoBean coinInfo) {
        this.coinInfo = coinInfo;
    }

    public ShareArticlesBean getShareArticles() {
        return shareArticles;
    }

    public void setShareArticles(ShareArticlesBean shareArticles) {
        this.shareArticles = shareArticles;
    }

    public static class CoinInfoBean {
        /**
         * coinCount : 900
         * level : 9
         * rank : 528
         * userId : 26308
         * username : l**_2019
         */

        private int coinCount;
        private int level;
        private int rank;
        private int userId;
        private String username;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class ShareArticlesBean {
        /**
         * curPage : 1
         * datas : []
         * offset : 0
         * over : true
         * pageCount : 0
         * size : 20
         * total : 0
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<?> datas;

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

        public List<?> getDatas() {
            return datas;
        }

        public void setDatas(List<?> datas) {
            this.datas = datas;
        }
    }
}
