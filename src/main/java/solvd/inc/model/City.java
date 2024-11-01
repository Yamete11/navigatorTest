package solvd.inc.model;

import java.util.List;

public class City {
    private Long id;
    private String title;
    private Double x;
    private Double y;


    public City() {
    }

    public City(Long id, String title, Double x, Double y) {
        this.id = id;
        this.title = title;
        this.x = x;
        this.y = y;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
