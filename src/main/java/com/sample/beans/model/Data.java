package com.sample.beans.model;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

@ManagedBean()
@ApplicationScoped
public class Data implements Serializable{

    private long id;
    private long date;
    private long value;
    private String description;

    public Data(){}


    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Data{" +
                "date=" + date +
                ", value=" + value +
                ", description='" + description + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (id != data.id) return false;
        if (date != data.date) return false;
        if (value != data.value) return false;
        return !(description != null ? !description.equals(data.description) : data.description != null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (date ^ (date >>> 32));
        result = (int) (31 * result + value);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
