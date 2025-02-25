import enigma.console.*;
public class Backpack{
	
	 Stack backpack=new Stack(8);
	public static void callBackpack(Stack s1,enigma.console.Console cn)
	{
		Stack tempstack=new Stack(8);
		//y=6,x=61 sol üst 
		//first we need to print the empty backpack
		int fx=61,fy=14;
		cn.getTextWindow().setCursorPosition(61, 14);
		while(!s1.isEmpty())
		{
			tempstack.push(s1.pop());
		}
		for(int i=0;i<9;i++)
		{
			cn.getTextWindow().setCursorPosition(fx, fy-i);
			if(i!=0)
			{
				System.out.print("| ");
				if(!tempstack.isEmpty())
				{
					System.out.print(tempstack.peek()+" |");
					s1.push(tempstack.pop());
				}
				else System.out.print("  |");
			}
			else System.out.print("+---+");
		}
		
	}
	public static void backpackManagment(Stack player_backpack) { // for reach the treasures
		 Object item1 =' ' ;  
         Object item2=' ' ;  
		if(!player_backpack.isEmpty())
		{
			 item1 = player_backpack.pop();  
			 if(!player_backpack.isEmpty())
			 {
				  item2 = player_backpack.pop();  
			 }
		}
            if (item1.equals(item2) ) {  
                if (item1==(Object)'1') {
					Gravity.score+=10;
                }else if(item1==(Object)'2') {
                	Gravity.score+=40;
                }else {
					Gravity.score+=90;
					Gravity.teleportationCount+=1;
				}
            } else {  
            	if((char)item1!=' ')
            	{
            		 player_backpack.push(item1);
            	}
            	if((char)item2!=' ')
            	{
                    player_backpack.push(item2);

            	}
               
            }
            
        
		   
	}
}
