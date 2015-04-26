package ovh.valulz.cvclient.model;

import java.util.Date;

public class Experience {

    private Date begin;
    private Date end;
    private String name;

    public Experience() {
        begin = new Date();
        end = new Date();
        name = "";
    }

    public Experience(Date begin, Date end, String name) {
        this.begin = begin;
        this.end = end;
        this.name = name;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
