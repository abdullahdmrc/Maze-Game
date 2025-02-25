import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Console;
import enigma.console.TextAttributes;
import java.awt.Color;
public class Gravity {
	public static enigma.console.Console cn = Enigma.getConsole("Gravity Game");
	   public TextMouseListener tmlis; 
	   public KeyListener klis; 

	   // ------ Standard variables for mouse and keyboard ------
	   public int mousepr;          // mouse pressed?
	   public int mousex, mousey;   // mouse text coordinates.
	   public int keypr;   // key pressed?
	   public int rkey;    // key   (for press/release)
	   // ----------------------------------------------------
	   public static boolean amIFreeToGo=true;
	   public static boolean robotEncounter=true;
	   static int px,py,timeUnit=0;
	   static int teleportationCount=3;
	   public static char[][] gamefield;
	   static int score=0;
	   Gravity() throws Exception {   // --- Constructor
	     gamefield= helper.initialize();
	     Backpack backpack=new Backpack();
	     backpack.callBackpack(backpack.backpack, cn);
	      // ------ Standard code for mouse and keyboard ------ Do not change
	      tmlis=new TextMouseListener() {
	         public void mouseClicked(TextMouseEvent arg0) {}
	         public void mousePressed(TextMouseEvent arg0) {
	            if(mousepr==0) {
	               mousepr=1;
	               mousex=arg0.getX();
	               mousey=arg0.getY();
	            }
	         }
	         public void mouseReleased(TextMouseEvent arg0) {}
	      };
	      cn.getTextWindow().addTextMouseListener(tmlis);
	    
	      klis=new KeyListener() {
	         public void keyTyped(KeyEvent e) {}
	         public void keyPressed(KeyEvent e) {
	            if(keypr==0) {
	               keypr=1;
	               rkey=e.getKeyCode();
	            }
	         }
	         public void keyReleased(KeyEvent e) {}
	      };
	      cn.getTextWindow().addKeyListener(klis);
	      // ----------------------------------------------------
	      
	       TextAttributes pColor=new TextAttributes(Color.RED);
	       px=5;py=5;
	      cn.getTextWindow().output(px,py,'P',pColor);
	      gamefield[py][px]='P';
	      Input.fgenerateInput();
	      Input.printInput(cn);
	      Robot rbt=new Robot();
	     gamefield= rbt.createRobots(gamefield);
	     String message="";
	     //MAIN GAME LOOP
	      while(true) {
	    	
	    	  timeUnit++;
	    	  cn.getTextWindow().setCursorPosition(59,22);
	    	  cn.getTextWindow().output("Time: "+Integer.toString(timeUnit/10));
	    	  cn.getTextWindow().setCursorPosition(59,23);
	    	  cn.getTextWindow().output("Score: "+Integer.toString(score));
	    	  cn.getTextWindow().setCursorPosition(59,24);
	    	  cn.getTextWindow().output("Teleport: "+Integer.toString(teleportationCount));
	    	  Input.printInput(cn);
	    	  if(timeUnit%30==0)
	    	  {
	    		 gamefield=Input.execute(gamefield);
	    		 Input.afterExec();
	    		 Input.printInput(cn);
	    	  }
	    	  cn.getTextWindow().setCursorPosition(0, 0);
	    	  helper.printGameArea(gamefield,cn);
	    	
	    	  backpack.callBackpack(backpack.backpack, cn);
	    	  gamefield=rbt.robotMovements(gamefield);
	         if(mousepr==1) {  // if mouse button pressed
	            cn.getTextWindow().output(mousex,mousey,'#');  // write a char to x,y position without changing cursor position
	            px=mousex; py=mousey;
	            
	            mousepr=0;     // last action  
	         }
	         if(keypr==1) {    // if keyboard button pressed
	        	 int prevx=px,prevy=py;
	            if(rkey==KeyEvent.VK_LEFT &&amIFreeToGo) px--;   
	            if(rkey==KeyEvent.VK_RIGHT&&amIFreeToGo) px++;
	            if(rkey==KeyEvent.VK_UP&&amIFreeToGo) py--;
	            if(rkey==KeyEvent.VK_DOWN&&amIFreeToGo) py++;
	            if(rkey==KeyEvent.VK_T&& teleportationCount!=0)
	            {
	            	gamefield=helper.teleportation(px, py, gamefield);
	            	teleportationCount--;
	            }
	            if(gamefield[py][px]=='#' || gamefield[py][px]=='O')
	            {
	            	px=prevx;
	            	py=prevy;
	            }
	            else if(gamefield[py][px]=='1' || gamefield[py][px]=='2' || gamefield[py][px]=='3')
	            {
	            	if(backpack.backpack.isFull())
	            	{
	            		backpack.backpack.pop();
	            	}
	            	backpack.backpack.push(gamefield[py][px]);
	            	backpack.backpackManagment(backpack.backpack);
	            	cn.getTextWindow().output(prevx,prevy,' ');
	            	gamefield[prevy][prevx]=' ';
	            }
	            else 
	            	{
	            	 if(rkey!=KeyEvent.VK_T)
	            	 {
	            		 cn.getTextWindow().output(prevx,prevy,' ');
	 	            	gamefield[prevy][prevx]=' ';
	            	 }
	            	
	            	}
	           
	        	  
	           
	            char rckey=(char)rkey;
	           
//	              left          right          up            down
		            if(rckey=='%' || rckey=='\'' || rckey=='&' || rckey=='(') 
		            	{
		            	cn.getTextWindow().output(px,py,'P',new TextAttributes(Color.RED)); // VK kullanmadan test teknigi
		            	gamefield[py][px]='P';
		            	}
	            
	            
	            
	            
	            if(rkey==KeyEvent.VK_SPACE) {
	               String str;         
	               str=cn.readLine();     // keyboardlistener running and readline input by using enter 
	               cn.getTextWindow().setCursorPosition(5, 20);
	               cn.getTextWindow().output(str);
	            }
	            
	            keypr=0;    // last action  
	         }
	         helper.boulderFall(gamefield);
	         if(!amIFreeToGo&&teleportationCount==0)
		    	{
	        	 message="Death By Boulder";
		    		break;
		    	}
	         if(!robotEncounter)
	         {
	        	 message="Death By Robot";
	        	 break;
	         }
	         
	         Thread.sleep(100);
	      }
	      //GAME OVER CONDITIONS
	      helper.printGameArea(gamefield,cn);
	      cn.getTextWindow().setCursorPosition(59,18);
	      cn.getTextWindow().output("GAME OVER "+message);
	      
	   }
}
