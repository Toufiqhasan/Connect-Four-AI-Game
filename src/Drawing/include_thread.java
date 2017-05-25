package Drawing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class include_thread extends Thread{

    
    
     public void run() {
     try {
         //System.out.println("**");
     Thread.sleep(2000);

     } catch (InterruptedException ex) {
     Logger.getLogger(include_thread.class.getName()).log(Level.SEVERE, null, ex);
     }

     }
    
     /*
    include_thread() {

        final JOptionPane msg = new JOptionPane("AI is Thinking");
        final JDialog dlg = msg.createDialog(" ");
        //dlg.setAlwaysOnTop(true);
        dlg.setSize(new Dimension(200,80));
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dlg.setLocation(580,100);
        
        dlg.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                final Timer t = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dlg.setVisible(false);
                    }
                });
                t.start();
               
            }
        });
        dlg.setVisible(true);

    }
    
    */

}
