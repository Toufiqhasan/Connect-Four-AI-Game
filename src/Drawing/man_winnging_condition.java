package Drawing;

import Drawing.Draw_circle;
import static java.lang.Math.max;

public class man_winnging_condition {

    boolean mmm() {

        ///System.out.println("THis is the sub class");
        ///  this is the vertical search for human winning condition
        int hgh_man = 0;
        for (int i = 1; i <= 7; i++) {

            int mn = 0;

            for (int j = 10; j <= 310; j += 60) {
                int rp = (i - 1) * 70 + 10;
                ///System.out.println("row and colom"+" "+rp+" "+j);
                if (Draw_circle.color[rp][j] == true && Draw_circle.value[rp][j] == 1) {

                    mn++;

                } else if (Draw_circle.color[rp][j] == true && Draw_circle.value[rp][j] == 2) {

                    break;

                }

            }
            hgh_man = max(hgh_man, mn);
        }

        ///System.out.println("the value of vertica; man:"+hgh_man);
        if (hgh_man >= 4) {

            return true;
        }

        hgh_man = 0;

        /// this is the horizontal search for human winning condition
        for (int i = 1; i <= 7; i++) {

            int mn = 0;

            for (int j = 10; j <= 310; j += 60) {
                int rp = (i - 1) * 70 + 10;
                if (Draw_circle.color[rp][j] == true && Draw_circle.value[rp][j] == 1) {

                    for (int k = rp; k <= 430; k += 70) {

                        if (Draw_circle.value[k][j] == 1) {
                            mn++;
                        } else {
                            break;
                        }
                    }

                    for (int k = rp - 70; k >= 10; k -= 70) {

                        if (Draw_circle.value[k][j] == 1) {
                            mn++;
                        } else {
                            break;
                        }

                    }
                    break;
                } else if (Draw_circle.color[rp][j] == true && Draw_circle.value[rp][j] == 2) {
                    break;
                }
            }
            hgh_man = max(hgh_man, mn);
        }
        ///System.out.println("the value of horizontal amn:"+hgh_man);
        if (hgh_man >= 4) {
            return true;
        } else {
            return false;
        }
    }
}
