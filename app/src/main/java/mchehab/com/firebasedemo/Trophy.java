package mchehab.com.firebasedemo;

import org.parceler.Parcel;

/**
 * Created by muhammadchehab on 1/10/18.
 */
@Parcel
public class Trophy {

    private String key;
    private String sport;
    private String year;
    private String description;

    public Trophy(){

    }

    public Trophy(String firstName, String lastName, String age){
        this.sport = firstName;
        this.year = lastName;
        this.description = age;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object object){
        if(object == null)
            return false;
        if(!Trophy.class.isAssignableFrom(object.getClass()))
            return false;
        final Trophy trophy = (Trophy)object;
        return trophy.getKey().equals(key);
    }
}
