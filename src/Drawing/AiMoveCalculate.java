package Drawing;

import static Drawing.Draw_circle.color;
import static Drawing.Draw_circle.total_value;
import static Drawing.Draw_circle.value;
import java.util.Arrays;

public class AiMoveCalculate {

    int[] GivePointHuman = new int[10];
    int[] GivePointAi = new int[10];
    int[] total_a = new int[10];
    int[] total_h = new int[10];
    int[] v = new int[10];

    AiMoveCalculate() {

        GivePointHuman[5] = 100;
        GivePointHuman[4] = 100;
        GivePointHuman[3] = 100;
        GivePointHuman[2] = 20;
        GivePointHuman[1] = 5;
        GivePointHuman[0] = 0;

        GivePointAi[5] = 200;
        GivePointAi[4] = 200;
        GivePointAi[3] = 200;
        GivePointAi[2] = 20; //40 hbe
        GivePointAi[1] = 10;
        GivePointAi[0] = 0;

    }

    public void vertical_search() {
        
         Draw_circle.ai_win = false;

        for (int i = 1; i <= 7; i++) {

            total_value[i] = 0;
        }

        for (int i = 1; i <= 7; i++) {
            int ase = 0;
            int cnt_ai = 0;
            int cnt_man = 0;
            for (int j = 10; j <= 310; j += 60) {
                int rp = (i - 1) * 70 + 10;
                ///  System.out.println("the value of j is "+rp+" "+j);
                if (color[rp][j] == false) {
                     ///System.out.println("***");

                    for (int k = j + 60; k <= 310; k += 60) {
                        if (value[rp][k] == 1 && color[rp][k] == true) {
                            cnt_man++;
                        } else {
                            break;
                        }
                    }

                    for (int k = j + 60; k <= 310; k += 60) {
                        if (value[rp][k] == 2 && color[rp][k] == true) {
                            cnt_ai++;
                        } else {
                            break;
                        }
                    }
                    ase = 1;
                    break;
                }
            }

            if (cnt_ai >= 3) {
                Draw_circle.ai_win = true;
            }
            total_value[i] += GivePointAi[cnt_ai];
            total_value[i] += GivePointHuman[cnt_man];
            ///System.out.println("the vertical value"+i+" "+cnt_ai+" "+cnt_man);
            ///System.out.println("the vertical value"+total_value[i]);

        }

    }

    public void horizontal_search() {

        for (int i = 1; i <= 7; i++) {

            total_a[i] = 0;
            total_h[i] = 0;
        }

        int hori_left_man = 0;
        int hori_left_ai = 0;
        int hori_right_man = 0;
        int hori_right_ai = 0;

        /// count for horizontal left
        for (int i = 1; i <= 7; i++) {

            hori_left_man = 0;
            hori_left_ai = 0;

            int ase = 0;
            for (int j = 10; j <= 310; j += 60) {
                
                
                ///System.out.println(" "+j);
                
                int rp = (i - 1) * 70 + 10;
                if (color[rp][j] == false) {

                    for (int k = rp - 70; k >= 10; k -= 70) {
                        if (value[k][j] == 1) {
                            hori_left_man++;
                        } else {
                            break;
                        }
                    }

                    for (int k = rp - 70; k >= 10; k -= 70) {
                        if (value[k][j] == 2) {
                            hori_left_ai++;
                        } else {
                            break;
                        }
                    }
                    ase = 1;
                    break;
                }
               

            }
            /// value assign accourding to their number

            total_a[i] += hori_left_ai;
            total_h[i] += hori_left_man;
            
             ///System.out.println("" + total_h[i] + " " + total_a[i]);

        }

        /// count for horizontal right
        for (int i = 1; i <= 7; i++) {
            hori_right_man = 0;
            hori_right_ai = 0;
            int v = 0;
            int ase = 0;
            for (int j = 10; j <= 310; j += 60) {
                int rp = (i - 1) * 70 + 10;
                if (color[rp][j] == false) {

                    for (int k = rp + 70; k <= 430; k += 70) {
                        if (value[k][j] == 1) {

                            hori_right_man++;
                        } else {
                            break;
                        }
                    }

                    for (int k = rp + 70; k <= 430; k += 70) {
                        if (value[k][j] == 2) {
                            hori_right_ai++;
                        } else {
                            break;
                        }
                    }
                    ase = 1;
                    break;
                }
            }

            /// value assign according to their number of elemets
            total_a[i] += hori_right_ai;
            total_h[i] += hori_right_man;
        }

        for (int i = 1; i <= 7; i++) {
            ////System.out.println("" + total_h[i] + " " + total_a[i]);
           /// System.out.println(""+total_a[i]);
            total_value[i] += GivePointHuman[total_h[i]];
            total_value[i] += GivePointAi[total_a[i]];
            
           /// System.out.println(" " +total_value[i]);
            if (total_a[i] >= 3) {
                Draw_circle.ai_win = true;
            }
        }
    }

    public int move() {
        
        ///System.out.println("in move");
        
        for (int i = 1; i <= 7; i++) {
            ///System.out.println(" "+total_value[i]);
        }

        int indx = 0;
        int mx = 0;

        int mx_indx = 0;
        

        int[] indx_color = new int[10];

        for (int i = 1; i <= 7; i++) {
            indx_color[i] = 0;
        }

        int c = 0;

        while (true) {
            c++;

            mx = 0;
            indx = 0;
        
            for (int k = 1; k <= 7; k++) {
            
                
                if (total_value[k] > mx && indx_color[k]==0) {
                    mx = total_value[k];
                    indx = k;
                }
            }
            
            if(Draw_circle.ai_win==true)
            {
                return indx;
            }
            
         
            if (mx == 0) {
                
                return mx_indx;

            }

            indx_color[indx] = 1;
            if (c == 1) {
                mx_indx = indx;
            }
            
            
            
            FutureMoveCalculate fc = new FutureMoveCalculate();
            boolean repeat = fc.check(indx);
            if (repeat == false) {
                
               break;
            }

        }

        return  indx;
    }

}
