package com.ect.earnkarle.bean;

import java.io.Serializable;

/**
 * Created by ABC on 4/11/2016.
 */
public class DoTaskListBean implements Serializable {

    String offer_id;
    String product_id;
    String title;
    String icon;
    String voting_star;
    String small_description;
    String package_name;
    String price;


    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getVoting_star() {
        return voting_star;
    }

    public void setVoting_star(String voting_star) {
        this.voting_star = voting_star;
    }

    public String getSmall_description() {
        return small_description;
    }

    public void setSmall_description(String small_description) {
        this.small_description = small_description;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

