package br.com.keyrus.samples;

public class KeyrusOffice {

    private String name;
    private String city;
    private String country;
    private String website;

    public KeyrusOffice(String name, String city, String country, String website) {
        super();
        this.name = name;
        this.city = city;
        this.country = country;
        this.website = website;
    }

    @Override
    public String toString() {
        return name + " (" + city + "-" + country + ") >> " + website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
