package Drawing;

import static Drawing.Draw_circle.color;
import static Drawing.Draw_circle.value;

public class FutureMoveCalculate {

    boolean check(int index) {

        int x = (index - 1) * 70 + 10;

        int cnt = 0;

        for (int j = 10; j <= 310; j += 60) {

            if (color[x][j] == false) {

                j = j - 60;

                if (j >= 10) {
                    for (int k = x + 70; k <= 430; k += 70) {
                        if (value[k][j] == 1) {

                            cnt++;
                        } else {
                            break;
                        }
                    }

                    for (int k = x - 70; k >= 10; k -= 70) {
                        if (value[k][j] == 1) {
                            cnt++;
                        } else {
                            break;
                        }
                    }

                }

                if (cnt >= 3) {
                    return true;
                } else {
                    return false;
                }

            }

        }

        return true;

    }

}
