package com.androidorange.example.firebasertdb;

import java.util.HashMap;

public class Player {

    String position;
    String name;
    double projection;

    public Player()
    {
        //Blank constructor required for Firebase Real Time Database.
    }

    public Player(String name, String position, double projection)
    {
        this.name = name;
        this.position = position;
        this.projection = projection;
    }

    public String getSummary()
    {
        return name + " " + position;
    }

    public String getName()
    {
        return name;
    }

    public String getPosition()
    {
        return position;
    }

    public double getProjection()
    {
        return projection;
    }

    public HashMap<String, Object> toHashMap()
    {
        HashMap<String, Object> value = new HashMap<>();

        value.put("name", name);
        value.put("position", position);
        value.put("projection", projection);

        return value;
    }

}
