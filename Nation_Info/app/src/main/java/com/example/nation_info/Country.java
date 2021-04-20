package com.example.nation_info;
public class Country{
    int id;
    String countryName;
    String countryFlag;
    String code;
    int population;
    double area;

    public Country(int id, String countryName, String code, int population, double area) {
        super();
        this.id = id;
        this.countryName = countryName;
        this.code = code;
        this.population = population;
        this.area = area;
    }
    public Country(int id, String countryName, int population, double area) {
        super();
        this.id = id;
        this.countryName = countryName;
        //this.countryFlag = countryFlag;
        this.population = population;
        this.area = area;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getCountryFlag() {
        return countryFlag;
    }
    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public double getArea() {
        return area;
    }
    public void setArea(double area) {
        this.area = area;
    }
}
/*public class Country{
    int id;
    String countryName;

    public Country(int i, String countryName) {
        super();
        this.id = i;
        this.countryName = countryName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}*/
