package com.androidorange.example.firebasertdb;

import java.util.ArrayList;
import java.util.Random;

public class FixedData {


    public static Player getRandomPlayer() {

        Random randomizer = new Random();
        int result = randomizer.nextInt(15);
        Player myPlayer;

        switch (result) {
            case 0:
                myPlayer = new Player("Brees", "QB", 280.2);
                break;
            case 1:
                myPlayer = new Player("Luck", "QB", 267.2);
                break;
            case 2:
                myPlayer = new Player("Roethlisberger", "QB", 264.9);
                break;
            case 3:
                myPlayer = new Player("Stafford", "QB", 264.1);
                break;
            case 4:
                myPlayer = new Player("Smith", "QB", 263.3);
            case 5:
                myPlayer = new Player("Gordon", "RB", 214.9);
                break;
            case 6:
                myPlayer = new Player("Barkley", "RB", 207.8);
                break;
            case 7:
                myPlayer = new Player("McCaffrey", "RB", 184.4);
                break;
            case 8:
                myPlayer = new Player("Ayaji", "RB", 150.9);
                break;
            case 9:
                myPlayer = new Player("Mixon", "RB", 164.1);
            case 10:
                myPlayer = new Player("Baldwin", "WR", 142.3);
                break;
            case 11:
                myPlayer = new Player("Tate", "WR", 128.6);
                break;
            case 12:
                myPlayer = new Player("Landry", "WR", 123.0);
                break;
            case 13:
                myPlayer = new Player("Thielen", "WR", 138.5);
                break;
            case 14:
            default:
                myPlayer = new Player("Beckham", "WR", 175.4);
        }

        return myPlayer;
    }

    public static ArrayList<Player> getRandomList() {
        Random randomizer = new Random();
        int result = randomizer.nextInt(6);
        ArrayList<Player> returnList = new ArrayList<>();

        switch (result) {
            case 0:
                returnList = getListQB();
                break;
            case 1:
                returnList = getListRB();
                break;
            case 2:
                returnList = getListWR();
                break;
            case 3:
                returnList = getListTE();
                break;
            case 4:
                returnList = getListDST();
                break;
            case 5:
            default:
                returnList = getListK();
        }

        return returnList;
    }

    private static ArrayList<Player> getListQB() {
        ArrayList<Player> myList = new ArrayList<>();

        myList.add(new Player("Rodgers", "QB", 321.1));
        myList.add(new Player("Brady", "QB", 299.5));
        myList.add(new Player("Wilson", "QB", 294.8));
        myList.add(new Player("Watson", "QB", 287.1));
        myList.add(new Player("myList.add( newton", "QB", 286.5));

        return myList;
    }

    private static ArrayList<Player> getListRB() {
        ArrayList<Player> myList = new ArrayList<>();
        myList.add(new Player("Gurley", "RB", 277.0));
        myList.add(new Player("Elliott", "RB", 251.6));
        myList.add(new Player("Johnson", "RB", 247.4));
        myList.add(new Player("Bell", "RB", 237.2));
        myList.add(new Player("Fournette", "RB", 217.5));
        return myList;
    }

    private static ArrayList<Player> getListWR() {
        ArrayList<Player> myList = new ArrayList<>();
        myList.add(new Player("Hopkins", "WR", 188.8));
        myList.add(new Player("Jones", "WR", 185.7));
        myList.add(new Player("Green", "WR", 159.9));
        myList.add(new Player("Hill", "WR", 153.5));
        myList.add(new Player("Hilton", "WR", 145.2));
        return myList;
    }

    private static ArrayList<Player> getListTE() {
        ArrayList<Player> myList = new ArrayList<>();
        myList.add(new Player("Gronkowski", "TE", 164.4));
        myList.add(new Player("Kelce", "TE", 139.8));
        myList.add(new Player("Olsen", "TE", 107.3));
        return myList;
    }

    private static ArrayList<Player> getListDST() {
        ArrayList<Player> myList = new ArrayList<>();
        myList.add(new Player("Gostkowski", "K", 142.6));
        myList.add(new Player("Zuerlein", "K", 135.2));
        myList.add(new Player("Tucker", "K", 130.2));
        return myList;
    }

    private static ArrayList<Player> getListK() {
        ArrayList<Player> myList = new ArrayList<>();
        myList.add(new Player("LA RAMS", "DST", 120.2));
        myList.add(new Player("EAGLES", "DST", 118.4));
        myList.add(new Player("VIKINGS", "DST", 116.0));
        return myList;
    }
}
