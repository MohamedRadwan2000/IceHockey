package IceHockey;
import java.math.*;

import java.awt.*;

public class icehockey implements IPlayersFinder{


    public java.awt.Point[] findPlayers( String[] photo, int team, int threshold) {

        try {
            java.awt.Point[] pixel = new java.awt.Point[photo.length * photo[0].length()];
            int counterpixel = 0;
            String str = Integer.toString(team);
            int counter1 = 0;
            int remain = 0;
            if (threshold % 4 != 0) {
                remain = 1;
            }
            Point[] right = new Point[(photo.length) * photo[0].length()];
            for (int i = 0; i < photo.length; i++) {
                for (int j = 0; j < photo[0].length(); j++) {
                    if (photo[i].charAt(j) == str.charAt(0)) {
                        right[counter1] = new Point(i, j);
                        counter1++;
                    }
                }
            }
            for (int i = 0; i < counter1; i++) {
                if ((right[i].x == -1) && (right[i].y == -1)) {
                    continue;
                }
                Point each_node[] = new Point[right.length];
                for (int k = 0; k < right.length; k++) {
                    each_node[k] = new Point(-1, -1);
                }
                each_node[0].setLocation(right[i].x, right[i].y);
                int counter2 = 1;
                for (int j = 1; j < counter1; j++) {
                    int dif = ((Math.abs(right[i].x - right[j].x)) + (Math.abs(right[i].y - right[j].y)));
                    if (dif == 1) {
                        each_node[counter2].setLocation(right[j].x, right[j].y);
                        counter2++;
                    }
                }
                for (int j = 1; j < counter2; j++) {
                    for (int k = 0; k < counter1; k++) {
                        int dif = (Math.abs(each_node[j].x - right[k].x) + Math.abs(each_node[j].y - right[k].y));
                        if (dif == 1) {
                            boolean n = true;
                            for (int y = 0; y < counter2; y++) {
                                if (right[k].equals(each_node[y])) {
                                    n = false;
                                }
                            }
                            if (n) {
                                each_node[counter2].setLocation(right[k].x, right[k].y);
                                counter2++;
                            }
                        }
                    }
                }
                if ((counter2 * 4) >= (threshold)) {
                    int x_min = each_node[0].x, x_max = x_min, y_min = each_node[0].y, y_max = y_min;
                    for (int m = 0; m < counter2; m++) {
                        if (each_node[m].x > x_max) {
                            x_max = each_node[m].x;
                        }
                        if (each_node[m].x < x_min) {
                            x_min = each_node[m].x;
                        }
                        if (each_node[m].y < y_min) {
                            y_min = each_node[m].y;
                        }
                        if (each_node[m].y > y_max) {
                            y_max = each_node[m].y;
                        }
                    }
                    pixel[counterpixel] = new Point(y_max + y_min + 1, x_max + x_min + 1);
                    counterpixel++;
                }
                for (int l = 0; l < counter2; l++) {
                    for (int k = 0; k < counter1; k++) {
                        if (each_node[l].equals(right[k])) {
                            right[k].setLocation(-1, -1);
                        }
                    }
                }
            }
            Point[] pixel2 = new Point[counterpixel];
            for (int k = 0; k < counterpixel; k++) {
                pixel2[k] = pixel[k];
            }
            for (int k = 0; k < counterpixel - 1; k++) {
                for (int l = 0; l < counterpixel - k - 1; l++) {
                    if (pixel2[l].x > pixel2[l + 1].x) {
                        Point temp = new Point(pixel2[l]);
                        pixel2[l] = pixel2[l + 1];
                        pixel2[l + 1] = temp;
                    }
                    if (pixel2[l].x == pixel2[l + 1].x) {
                        if (pixel2[l].y > pixel2[l + 1].y) {
                            Point temp = new Point(pixel2[l]);
                            pixel2[l] = pixel2[l + 1];
                            pixel2[l + 1] = temp;
                        }
                    }
                }
            }
            for (int k = 0; k < counterpixel; k++) {
                System.out.println(pixel2[k]);
            }




            return pixel2;
        }
        catch(Exception e){
            Point[] p = new Point[]{};
            return p;
        }
    }
}
