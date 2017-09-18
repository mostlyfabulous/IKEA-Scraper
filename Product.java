package com.company;

import com.sun.javafx.font.Metrics;

/**
 * Created by Andrew on 16/09/2017.
 */
public class Product {
    private String Product_name;
    private String Price;
    private String Metric;
    private String Imperial;

    public Product (String pname, String price, String metric, String imp){
        this.Product_name = pname;
        this.Price = price;
        this.Metric = metric;
        this.Imperial = imp;
    }

    public Product() {

    }

    @Override public String toString () {
        return Product_name + ";" + Price + ";" + Metric + ";" + Imperial;
    }
}

