package com.lwj.persistence.pojo;

import java.util.Date;

public class ComplaintKey {
    private Integer complainer;

    private Integer complainee;

    private Date time;

    public Integer getComplainer() {
        return complainer;
    }

    public void setComplainer(Integer complainer) {
        this.complainer = complainer;
    }

    public Integer getComplainee() {
        return complainee;
    }

    public void setComplainee(Integer complainee) {
        this.complainee = complainee;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}