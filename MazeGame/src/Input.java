import java.util.*;
public class Input {
private static queue Input=new queue(15);
static Random rand=new Random();
//Probability chart
//1-10 a boulder is placed instead of a earth square,and an earth square is placed instead of a boulder.So what we going to do is first delete a boulder and then add a boulder
//11-19 an empty square will be filled with an earth square
//20-24 an earth square will emptied
//25-30 treasure 1 will be generated
//31-35 treasure 2 will be generated
//36-39 treasure 3 will be generated
//40 a robot will be spawned
public static void fgenerateInput()
{
	
	int selection;
	for(int i=0;i<15;i++)
	{
		selection=rand.nextInt(40)+1;
		if(selection>0 && selection<11)
		{
			Input.enqueue('O');
		}
		else if(selection>10&&selection<21)
		{
			Input.enqueue(':');
		}
		else if(selection>20&&selection<26)
		{
			Input.enqueue('e');
		}
		else if(selection>25&&selection<31)
		{
			Input.enqueue('1');
		}
		else if(selection>30&&selection<36)
		{
			Input.enqueue('2');
		}
		else if(selection>35&&selection<41)
		{
			Input.enqueue('3');
		}
	}
}
public static void afterExec()
{
	int selection;
	selection=rand.nextInt(40)+1;
	if(selection>0 && selection<11)
	{
		Input.enqueue('O');
	}
	else if(selection>10&&selection<21)
	{
		Input.enqueue(':');
	}
	else if(selection>20&&selection<26)
	{
		Input.enqueue('e');
	}
	else if(selection>25&&selection<31)
	{
		Input.enqueue('1');
	}
	else if(selection>30&&selection<36)
	{
		Input.enqueue('2');
	}
	else if(selection>35&&selection<41)
	{
		Input.enqueue('3');
	}
}
public static void printInput(enigma.console.Console cn)
{
	queue tempQ=new queue(15);
	char toBeAdded=' ',first=' ';
	cn.getTextWindow().setCursorPosition(56, 1);
	cn.getTextWindow().output("<<<<<<<<<<<<<<");
	cn.getTextWindow().setCursorPosition(56, 2);
	for(int i=0;i<15;i++)
	{
		 toBeAdded=(char)(Input.dequeue());
		 if(i==0)first=toBeAdded;
		tempQ.enqueue(toBeAdded);
		cn.getTextWindow().output(56+i,2,toBeAdded);
	}
	for(int i=0;i<15;i++)
	{
		Input.enqueue(tempQ.dequeue());
	}
	
	cn.getTextWindow().setCursorPosition(56, 3);
	cn.getTextWindow().output("<<<<<<<<<<<<<<");
	cn.getTextWindow().setCursorPosition(56, 4);
	cn.getTextWindow().output("Element next: "+first);
}
public static char[][] execute(char[][] gamefield)
{
	char toBeExecuted=(char)Input.dequeue();
	int[]coords=new int[2];
	switch(toBeExecuted)
	{
	case '1':
	{
	 coords=helper.placeForT();
		gamefield[coords[1]][coords[0]]='1';
		break;
	}
	case '2':
	{
		 coords=helper.placeForT();
		gamefield[coords[1]][coords[0]]='2';
		break;
	}
	case '3':
	{
	 coords=helper.placeForT();
		gamefield[coords[1]][coords[0]]='3';
		break;
	}
	case 'O':
	{
	 coords=helper.findASpace();
		gamefield[coords[1]][coords[0]]='O';
		coords=helper.findABoulder();
		gamefield[coords[1]][coords[0]]=' ';
		break;
	}
	case ':':
	{
		coords=helper.findASpace();
		gamefield[coords[1]][coords[0]]=':';
		break;
	}
	case 'e':
	{
		coords=helper.findEarth();
		gamefield[coords[1]][coords[0]]=' ';
		break;
	}
	}
	return gamefield;
}
}
