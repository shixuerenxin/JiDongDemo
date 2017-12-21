package com.bwei.jidongdemo.bean;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/11/27.
 */

public class ShopBean {

        private String sellerName;
        private String sellerid;
        private List<ListBean> list;
        private Boolean group;

    public ShopBean(){}
        public ShopBean(String sellerName, String sellerid, List<ListBean> list, Boolean group) {
            this.sellerName = sellerName;
            this.sellerid = sellerid;
            this.list = list;
            this.group = group;
        }

        public Boolean getGroup() {
            return group;
        }

        public void setGroup(Boolean group) {
            this.group = group;
        }
        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getSellerid() {
            return sellerid;
        }

        public void setSellerid(String sellerid) {
            this.sellerid = sellerid;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * bargainPrice : 11800.0
             * createtime : 2017-10-14T21:38:26
             * detailUrl : https://mitem.jd.hk/ware/view.action?wareId=1988853309&cachekey=1acb07a701ece8d2434a6ae7fa6870a1
             * images : https://m.360buyimg.com/n0/jfs/t6130/97/1370670410/180682/1109582a/593276b1Nd81fe723.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5698/110/2617517836/202970/c9388feb/593276b7Nbd94ef1f.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5698/110/2617517836/202970/c9388feb/593276b7Nbd94ef1f.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5815/178/2614671118/51656/7f52d137/593276c7N107b725a.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5878/60/2557817477/30873/4502b606/593276caN5a7d6357.jpg!q70.jpg
             * num : 1
             * pid : 80
             * price : 777.0
             * pscid : 40
             * selected : 1
             * sellerid : 1
             * subhead : 购买电脑办公部分商品满1元返火车票5元优惠券（返完即止）
             * title : 全球购 新款Apple MacBook Pro 苹果笔记本电脑 银色VP2新13英寸Bar i5/8G/256G
             */

            private double bargainPrice;
            private String createtime;
            private String detailUrl;
            private String images;
            private int num;
            private int pid;
            private double price;
            private int pscid;
            private int selected;
            private int sellerid;
            private String subhead;
            private String title;
            private Boolean child;
            public ListBean(){}

            public ListBean(double bargainPrice, String createtime, String detailUrl, String images, int num, int pid, double price, int pscid, int selected, int sellerid, String subhead, String title, Boolean child) {
                this.bargainPrice = bargainPrice;
                this.createtime = createtime;
                this.detailUrl = detailUrl;
                this.images = images;
                this.num = num;
                this.pid = pid;
                this.price = price;
                this.pscid = pscid;
                this.selected = selected;
                this.sellerid = sellerid;
                this.subhead = subhead;
                this.title = title;
                this.child = child;
            }

            public Boolean getChild() {
                return child;
            }

            public void setChild(Boolean child) {
                this.child = child;
            }

            public double getBargainPrice() {
                return bargainPrice;
            }

            public void setBargainPrice(double bargainPrice) {
                this.bargainPrice = bargainPrice;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getDetailUrl() {
                return detailUrl;
            }

            public void setDetailUrl(String detailUrl) {
                this.detailUrl = detailUrl;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getPscid() {
                return pscid;
            }

            public void setPscid(int pscid) {
                this.pscid = pscid;
            }

            public int getSelected() {
                return selected;
            }

            public void setSelected(int selected) {
                this.selected = selected;
            }

            public int getSellerid() {
                return sellerid;
            }

            public void setSellerid(int sellerid) {
                this.sellerid = sellerid;
            }

            public String getSubhead() {
                return subhead;
            }

            public void setSubhead(String subhead) {
                this.subhead = subhead;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
}
