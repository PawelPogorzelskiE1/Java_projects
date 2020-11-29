package com.company;

public class MapConfig {
    public String[][] matrix;
    public int [] params;

    public MapConfig(int x, int y)
    {
        matrix = new String[x][y];
        params = new int[3];
    }
    public MapConfig()
    {

    }

}
