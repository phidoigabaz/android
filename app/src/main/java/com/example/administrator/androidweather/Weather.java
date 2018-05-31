package com.example.administrator.androidweather;

/**
 * Created by administrator on 5/31/18.
 */

public class Weather {
    private String name;
    private Double temp;
    private String icon;
    public Weather(String name,Double temp,String icon) {
        this.name = name;
        this.temp = temp;
        this.icon = icon;
    }
    public Weather(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "name='" + name + '\'' +
                ", temp=" + temp +
                ", icon='" + icon + '\'' +
                '}';
    }
}
