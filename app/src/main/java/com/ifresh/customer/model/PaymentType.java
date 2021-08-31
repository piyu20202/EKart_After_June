package com.ifresh.customer.model;

public class PaymentType {
    String id, title, abv;
    Boolean flag;

    public PaymentType(String id, String title, String abv, Boolean flag) {
        this.id = id;
        this.title = title;
        this.abv = abv;
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbv() {
        return abv;
    }


    public void setAbv(String abv) {
        this.abv = abv;
    }

    public Boolean getFlag() {
        return flag;
    }

}
