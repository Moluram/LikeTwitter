package twitter.beans;

import java.util.Date;

public class Tweet {

    private Integer id ;
    private String text;
    private Date date;


    public Tweet(){
        super();
    }



    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



}
