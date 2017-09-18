package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.lang.model.element.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Andrew on 16/09/2017.
 */
public class Scraper {

    public HashSet<Product> p;

    public Scraper () {
        p = new HashSet<>();
    }

    public void URLfetcher () throws IOException {
        //Document doc = Jsoup.connect("http://www.ikea.com/ca/en/catalog/categories/collections/36841/").data("query", "Java")
        //Document doc = Jsoup.connect("http://www.ikea.com/ca/en/catalog/categories/departments/decoration/10789/").data("query", "Java")
        Document doc = Jsoup.connect("http://www.ikea.com/ca/en/catalog/categories/departments/decoration/10789/").data("query", "Java")
//                Below is to mimic browser info being passed to website
//                .userAgent("Mozilla")
//                .cookie("auth", "token")
//                .timeout(3000)
//                .post() // parses result as well
                .get(); // parses result as well
        String title = doc.title();
        Elements product_urls = doc.getElementsByAttributeValueStarting("href", "/ca/en/catalog/products"); // "http://www.ikea.com//ca/en/search/?query=picture+frames&amp;pageNumber=2"
        Elements product_pg;
        String[] stuff = new String[product_urls.size()];
        int i =0;
        Document d;

        for (org.jsoup.nodes.Element products : product_urls) {
//         ln(products.attr("href")); // prints URLs to product pages
            stuff[i] = products.attr("href");
            d = Jsoup.connect(products.attr("abs:href")).get(); //get absolute URL of product and parse it into a Document for data extraction
            
         p.add(new Product((d.getElementsByClass("productName").text()),
                 (d.getElementsByClass("packagePrice").text()),
                 (d.getElementById("metric").text()),
                 (d.getElementById("imperial").text()) ));
//          = (d.getElementsByClass("productName").text());
//          = (d.getElementsByClass("packagePrice").text());
//          = (d.getElementById("metric").text());
//          = (d.getElementById("imperial").text());
            i++;
        }

    }

    public static void main(String[] args) {
        Scraper s = new Scraper();
        try {
            s.URLfetcher();
            for (Product i: s.p) {
                System.out.println(i.toString());

            }
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

        System.out.println("git ready");
    }
}


