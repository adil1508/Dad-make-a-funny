package com.example.dadmakeafunny;

/*
Joke class containing all the jokes as strings
 */

public class Joke {
<<<<<<< HEAD

    private String title;
    private String text;
    private String link;

    public Joke(){
        this.title = null;
        this.text = null;
        this.link = null;
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



=======
    public String title;
    public String text;

    public Joke(String title, String text){
        this.title = title;
        this.text = text;
    }

    public Joke(){
        this.title = null;
        this.text = null;
    }
>>>>>>> half implementation of JokeReader and Jokes\n Not functioning but decent desgin code
}
