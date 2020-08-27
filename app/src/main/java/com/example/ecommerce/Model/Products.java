package com.example.ecommerce.Model;

public class Products
{
    private String categoryName,Description,Price,Pname,image ,pid,date,time;

    public Products()
    {
    }

    public Products(String categoryName, String description, String price, String pname, String image, String pid, String date, String time) {
        this.categoryName = categoryName;
        Description = description;
        Price = price;
        Pname = pname;
        this.image = image;
        this.pid = pid;
        this.date = date;
        this.time = time;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
