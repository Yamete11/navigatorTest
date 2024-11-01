package solvd.inc.ui;

import java.util.List;
import solvd.inc.model.City;

public class CityMap {
    private static final int X = 103;
    private static final int Y = 33;

    public void displayCities(List<City> cities) {
        char[][] grid = new char[Y][X];


        for (int i = 1; i < Y; i++) {
            for (int j = 1; j < X; j++) {
                if (i == 1 || i == Y - 1) {
                    grid[i][j] = '-';
                } else if (j == 1 || j == X - 1) {
                    grid[i][j] = '|';
                } else {
                    grid[i][j] = ' ';
                }
            }
        }
        for (City city : cities) {
            int x = (int) Math.min(city.getX(), X);
            int y = (int) Math.min(city.getY(), Y);

            String cityId = String.valueOf(city.getId());
            int startCol = x + 1;
            for (int k = 0; k < cityId.length() && startCol < X - 1; k++) {
                grid[y + 1][startCol++] = cityId.charAt(k);
            }
        }


        for (int i = Y - 1; i >= 1; i--) {
            for (int j = 1; j < X; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
}
