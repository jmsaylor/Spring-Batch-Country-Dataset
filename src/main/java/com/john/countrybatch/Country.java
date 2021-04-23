package com.john.countrybatch;

public class Country {
    String name;
    String region;

    Country(){}

    public Country(String name, String region) {
        super();
        this.name = name;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


}
