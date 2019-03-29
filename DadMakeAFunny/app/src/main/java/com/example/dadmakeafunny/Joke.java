package com.example.dadmakeafunny;

/*
Joke class containing all the jokes as strings
 */

public class Joke {
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
}
