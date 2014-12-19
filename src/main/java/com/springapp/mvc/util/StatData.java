package com.springapp.mvc.util;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 19/Dec/2014
 */
public class StatData {

    private String country;
    private String percent;

    public StatData() {}

    public StatData(String country, String percent) {
        this.country = country;
        this.percent = percent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
