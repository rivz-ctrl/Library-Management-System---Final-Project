package com.library.domain;

public class DVD extends Item{
    private String director;
    private int duration;

    public DVD(String title, ItemStatus status, String director, int duration) {
        super(title, status);
        this.director = director;
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String getItemInfo() {
        return "DVD{" +
                "director='" + director + '\'' +
                ", duration=" + duration +
                ", itemId='" + itemId + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public String toString() {
        return getItemInfo();
    }
}
