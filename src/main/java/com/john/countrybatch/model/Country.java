package com.john.countrybatch.model;

public class Country {
    String name;
    String region;
    Integer population;

    Country(){}

    public Country(String name, String region, Integer population) {
        super();
        this.name = name;
        this.region = region;
        this.population = population;
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

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}
