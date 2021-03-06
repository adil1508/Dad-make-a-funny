package com.example.dadmakeafunny;

/*
Joke class containing all the jokes as strings
 */


public class Joke {

    private String title;
    private String text;
    private String link;

    public Joke(){
        this.title = null;
        this.text = null;
        this.link = null;
    }

    public Joke(String title, String text, String link){
        this.title = title;
        this.text = text;
        this.link = link;
    }

    //getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }
}
