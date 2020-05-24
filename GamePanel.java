/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author arpitasikder
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener{
    
    Timer timer = new Timer(2,this);
    int speedx=0, speedy=0, x=10, y=10;
    int keycode;
    int score=0;
    
    Random rand = new Random(); 
       
  // int [] arrX = new int[5];  
  // int [] arrY = new int[5];
 
      ArrayList<Integer> arrX = new ArrayList<>();
        ArrayList<Integer> arrY = new ArrayList<>();
   
    
    public GamePanel(){
        
    super.setDoubleBuffered(true);
   if(arrX.size()==0 && arrY.size()==0){
     //   arrX.add(rand.nextInt(500));
     //   arrY.add(rand.nextInt(500));
       arrX.add(80);
        arrY.add(80);
       
      } 
         
    timer.start();
    addKeyListener(this);
    setFocusable(true); //enable the keyListener
    setFocusTraversalKeysEnabled(false); //nt using shift and other
       
    }
    
    @Override
    public void paintComponent(Graphics g){
       
        super.paintComponent(g);
       
        
     
        g.setColor(Color.red);
        g.fillOval(arrX.get(0), arrY.get(0), 30, 30);
  
        if((keycode == KeyEvent.VK_RIGHT && speedx!=-1) || (keycode == KeyEvent.VK_LEFT && speedx==1)){ //only go right if the snake is not already going left
         
            
        g.setColor(Color.blue);
        g.fillOval(x, y, 30, 30);  
        g.fillOval(x+30, y, 30, 30);
        g.setColor(Color.black);
        g.fillOval(x+60, y+5, 10, 10);
        g.fillOval(x+60, y+18, 10, 10);
        
        
       
        }
    
        if((keycode == KeyEvent.VK_LEFT && speedx!=1) || (keycode == KeyEvent.VK_RIGHT && speedx ==-1 )) { //only go left if the snake is not already going right 
        g.setColor(Color.blue);
        g.fillOval(x, y, 30, 30);  
        g.fillOval(x+30, y, 30, 30);
        g.setColor(Color.black);
        g.fillOval(x-10, y+5, 10, 10);
        g.fillOval(x-10, y+18, 10, 10);
        
       
        }
   
       if ((keycode == KeyEvent.VK_DOWN && speedy!=-1) || (keycode == KeyEvent.VK_UP && speedy==1)){  //only go down if the snake is not already going up
        g.setColor(Color.blue);
        g.fillOval(x, y, 30, 30);  
        g.fillOval(x, y+30, 30, 30);
        g.setColor(Color.black);
        g.fillOval(x+5, y+60, 10, 10);
        g.fillOval(x+18, y+60, 10, 10);
        

       }
       
 
       if((keycode == KeyEvent.VK_UP && speedy!=1) || (keycode == KeyEvent.VK_DOWN && speedy==-1) ){  //only go up if the snake is not already going down 
        g.setColor(Color.blue);
        g.fillOval(x, y, 30, 30);   
        g.fillOval(x, y+30, 30, 30);
        g.setColor(Color.black);
        g.fillOval(x+5, y-10, 10, 10);
        g.fillOval(x+18, y-10, 10, 10);
  
       }
 
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //allows the snake to come back from the opposite side onces its goes beyond the boundary
      
        if(x>550){
            x=0;
        }
        if(y>550){
            y=0;
        }
        if(x<0){
         x=550;
        }
        if(y<0){
        y=550;
        }
        
        x=x+speedx;
        y=y+speedy;
        
      
   
       if(x==arrX.get(0) || y==arrY.get(0)){
       arrX.remove(0);
       arrY.remove(0);
       arrX.add(rand.nextInt(500));
       arrY.add(rand.nextInt(500));
       score++;
       } 
        
       repaint();
       
      System.out.print("YOUR SCROE:"+ score+ "\n");
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         keycode = ke.getKeyCode(); //returns the integer key code associated with the associated key
        if(keycode == KeyEvent.VK_LEFT && speedx!=1){
            speedx=-1;
            speedy=0;
            
        }
         if(keycode == KeyEvent.VK_RIGHT && speedx!=-1){
            speedx=1;
            speedy=0;
            
        }
          if(keycode == KeyEvent.VK_UP && speedy!=1){
            speedx=0;
            speedy=-1; //dont know why not positive
          
        }
           if(keycode == KeyEvent.VK_DOWN && speedy!=-1){
            speedx=0;
            speedy=1;
           
        }
           
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      
    }
    
    
   public static void main(String[] args) {
        // TODO code application logic here
      GamePanel gp = new GamePanel();
      SnakeGame s = new SnakeGame();
//      s.setTitle("SnakeGame");
      s.setTitle("SnakeGame");
      s.setSize(600,600);
      s.setLocation(100,100);
      s.add(gp);
      s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      s.setVisible(true);
      
       // frame.setVisible(true);
     
    }
}
