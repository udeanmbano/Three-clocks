/*************************************************************************
 *  Author : Udean Mbano
 *  Student Number: MGI2012-3244
 *  Date :30/07/2014
 *  Dependencies: StillClock.java
 *  Version:Version 14.2
 *  A Java Applet that displays three java clocks in a
   group, with control buttons to start and stop all of them.Three java clocks 
   run independently with individual control and group control.
 *
 *************************************************************************/

package mgi;
	
    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    import javax.swing.Timer;
//Import the StillClock.java class
     import mgi.StillClock;
    public class Exercise18_14 extends JApplet {
      // Declare three java clock panels
      private SwitchBoard firstClock, secondClock, thirdClock;
     
      // Declare the two main group control buttons to be used
      private JButton allresume, allsuspend;
     
      /** This main method enables the Applet to run as an application
     * @param args */
      public static void main(String[] args) {
        // Create a threejavaclocks
        JFrame threejavaclocks = new JFrame("Exercise18_14");
     
        // Create an instance of the applet
        Exercise18_14 applet = new Exercise18_14();
     
        // Add the applet instance to the threejavaclocks
        threejavaclocks.add(applet, BorderLayout.CENTER);
     
        // Invoke the to methods init() and start()
        applet.init();
        applet.start();
     
        // Display the three java clocks with a specified size
        threejavaclocks.setSize(800, 400);
        // positioning the three javaclocks in the center
        threejavaclocks.setLocationRelativeTo(null);
        threejavaclocks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        threejavaclocks.setVisible(true);
        // set resizable to false
        threejavaclocks.setResizable(false);
      }
     
      /*Init method to intialise the applet*/
      @Override
      public void init() {
        // Panel firstpanel for holding three javaclocks
        JPanel firstpanel = new JPanel();
        firstpanel.setLayout(new GridLayout(1, 3));
     
        // Displays the first javaclock
        firstpanel.add(firstClock = new SwitchBoard());
     
       // Displays the second javaclock
        firstpanel.add(secondClock = new SwitchBoard());
     
        // Displays the third javaclock
        firstpanel.add(thirdClock = new SwitchBoard());
     
        // Panel secondpanel for holding two group control buttons
       JPanel secondpanel = new JPanel();
       secondpanel.setLayout(new FlowLayout());
       secondpanel.add(allresume = new JButton("Resume All"));
       secondpanel.add(allsuspend = new JButton("Suspend All"));
     
        // Add panel firstpanel andsecondpanel into the applet
        setLayout(new BorderLayout());
        add(firstpanel, BorderLayout.CENTER);
        add(secondpanel, BorderLayout.SOUTH);
     
        // Register listeners for all the buttons used
        allresume.addActionListener(new ActionListener() {
          @Override
            public void actionPerformed(ActionEvent e) {
                  // Start all javaclocks on the first panel
                  firstClock.resume();
                  secondClock.resume();
                  thirdClock.resume();          
            }
        });
        allsuspend.addActionListener(new ActionListener() {
          @Override
            public void actionPerformed(ActionEvent e) {
                //stop all javaclocks the first panel
                  firstClock.suspend();
                  secondClock.suspend();
                  thirdClock.suspend();  
            }
        });
      }
    }
     
    class SwitchBoard extends JPanel {
        //create an instance of the class Clock
        // declare buttons to control the javaclocks
      private Clock javaclock = new Clock();
      private final JButton suspendbtn =new JButton("Suspend");
      private final JButton resumbtn = new JButton("Resume");
     
      public SwitchBoard() {
        // Group buttons in a panel
        JPanel panel = new JPanel();
        panel.add(suspendbtn);
        panel.add(resumbtn);
     
        // Add javaclock and buttons to the panel
        setLayout(new BorderLayout());
        add(javaclock, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
     
        // Register listeners
        suspendbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            javaclock.suspend();
          }        
        });
        resumbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            javaclock.resume();
          }        
        });
      }
     
      public void suspend() {
        javaclock.suspend();
      }
     
      public void resume() {
        javaclock.resume();
      }
     
      class Clock extends StillClock {
        private final Timer clocktimer = new Timer(1000, new Listener());
       
        public Clock() {
          clocktimer.start();
        }
     
        class Listener implements ActionListener {
          @Override
          public void actionPerformed(ActionEvent e) {
            // Set new time and repaint the javaclock to display current time
            setCurrentTime();
            repaint();
          }
        }
     
        public void suspend() {
          clocktimer.stop();
        }
     
        public void resume() {
          clocktimer.start();
       }
      }
    }

