import java.awt.Color;
import java.util.Random;

import enigma.console.TextAttributes;

public class helper {
	//initialization walls
public static char[][] initialize()
{
	char[][] gamefield=new char[25][55];;
	for(int i=0;i<25;i++)
	{
		for(int j=0;j<55;j++)
		{
			if(i==0 || i==24)
			{
				
				gamefield[i][j]='#';
				
			}
			else if(i==8 && j<50)
			{
					
					gamefield[i][j]='#';
			}
			else if(i==16 && j>4)
			{
				
				gamefield[i][j]='#';
			}
			else
			{
				if(j==0 || j==54)
					{
					
					gamefield[i][j]='#';
					}
				else 
					{
					gamefield[i][j]=':';
					}
			}
		}
	}
	gamefield=numberGeneration(gamefield);
	gamefield=boulderGeneration(gamefield);
	gamefield=spaceGeneration(gamefield);
	gamefield=boulderFall(gamefield);
	return gamefield;
}
public static char[][] spaceGeneration(char[][] gamefield)
{
	Random rand=new Random();
	boolean yvalid=false,xvalid=false;
	int xrand,yrand;
	for(int i=0;i<200;i++)
	{
		while(!yvalid&&!xvalid)
		{
			xrand=rand.nextInt(53)+1;
			yrand=rand.nextInt(23)+1;
			if(gamefield[yrand][xrand]==':')
			{
				gamefield[yrand][xrand]=' ';
				yvalid=true;
				xvalid=true;
			}
		}
		xvalid=false;
		yvalid=false;
	}
	return gamefield;
}
public static char[][] numberGeneration(char[][]gamefield)
{
	Random rand=new Random();
	boolean yvalid=false,xvalid=false;
	int xrand,yrand,prand;
	for(int i=0;i<30;i++)
	{
		while(!yvalid&&!xvalid)
		{
			xrand=rand.nextInt(53)+1;
			yrand=rand.nextInt(23)+1;
			if(gamefield[yrand][xrand]==':')
			{
				prand=rand.nextInt(3)+1;
				gamefield[yrand][xrand]=(char)(prand+'0');
				yvalid=true;
				xvalid=true;
			}
		}
		xvalid=false;
		yvalid=false;
	}
	return gamefield;
}
public static char[][] boulderGeneration(char[][]gamefield)
{
	Random rand=new Random();
	boolean yvalid=false,xvalid=false;
	int xrand,yrand;
	for(int i=0;i<180;i++)
	{
		while(!yvalid&&!xvalid)
		{
			xrand=rand.nextInt(53)+1;
			yrand=rand.nextInt(23)+1;
			if(gamefield[yrand][xrand]==':')
			{
				gamefield[yrand][xrand]='O';
				yvalid=true;
				xvalid=true;
			}
		}
		xvalid=false;
		yvalid=false;
	}
	return gamefield;
}
public static void printGameArea(char[][] gamefield,enigma.console.Console cn)
{
	for(int i=0;i<25;i++)
	{
		for(int j=0;j<55;j++)
		{
			
			if(gamefield[i][j]=='P')
			cn.getTextWindow().output(j,i,gamefield[i][j],new TextAttributes(Color.RED));
			else cn.getTextWindow().output(j,i,gamefield[i][j]);
		}
	}
}
public static char[][] boulderFall(char[][]gamefield)
{
	boolean goodToGo=false;
	while(!goodToGo)
	{
	for(int i=1;i<24;i++)
	{
		for(int j=0;j<55;j++)
		{
			if(gamefield[i][j]=='O' &&gamefield[i+1][j]==' ')
			{
				if(gamefield[i+2][j]=='P')
				{
					Gravity.amIFreeToGo=false;
				}
				if(gamefield[i+2][j]=='O')
				{
					if(gamefield[i+1][j-1]==' '&&gamefield[i+2][j-1]==' ')
						{
							gamefield[i][j]=' ';
							gamefield[i+2][j-1]='O';
							goodToGo=true;
							continue;
						}
					if(gamefield[i+1][j+1]==' '&&gamefield[i+2][j+1]==' ')
					{
						gamefield[i][j]=' ';
						gamefield[i+2][j+1]='O';
						goodToGo=true;
						continue;
					}
				}
				gamefield[i][j]=' ';
				gamefield[i+1][j]='O';
				goodToGo=true;
			}
			else continue;
		}
	}
	goodToGo=!goodToGo;
	}
	return gamefield;
}
public static char[][] teleportation(int x,int y,char[][] gamefield)
{
	Random rand=new Random();
	int prevx=x,prevy=y;
	boolean canITeleport=false;
	while(!canITeleport)
	{
		x=rand.nextInt(53)+1;
		y=rand.nextInt(23)+1;
		if(gamefield[y][x]==' ')
			{
			canITeleport=!canITeleport;
			gamefield[prevy][prevx]=' ';
			gamefield[y][x]='P';
			canITeleport=true;
			Gravity.amIFreeToGo=true;
			Gravity.px=x;
			Gravity.py=y;
			}
	}
	return gamefield;
}
public static int[] findASpace()
{
	Random rand=new Random();
	int x,y;
	boolean eligible=false;
	int[] tobereturned=new int[2];
	while(!eligible)
	{
		x=rand.nextInt(53)+1;
		y=rand.nextInt(23)+1;
		if(Gravity.gamefield[y][x]==' ')
		{
			eligible=true;
			tobereturned[0]=x;
			tobereturned[1]=y;
		}
	}
	return tobereturned;
}
public static int[] findABoulder()
{
	Random rand=new Random();
	int x,y;
	boolean eligible=false;
	int[] tobereturned=new int[2];
	while(!eligible)
	{
		x=rand.nextInt(53)+1;
		y=rand.nextInt(23)+1;
		if(Gravity.gamefield[y][x]=='O')
		{
			eligible=true;
			tobereturned[0]=x;
			tobereturned[1]=y;
		}
	}
	return tobereturned;
}
public static int[] placeForT() 
{
	Random rand=new Random();
	int x,y;
	boolean eligible=false;
	int[] tobereturned=new int[2];
	while(!eligible)
	{
		x=rand.nextInt(53)+1;
		y=rand.nextInt(23)+1;
		if(Gravity.gamefield[y][x]==' ' ||Gravity.gamefield[y][x]==':')
		{
			eligible=true;
			tobereturned[0]=x;
			tobereturned[1]=y;
		}
	}
	return tobereturned;
}
public static int[] findEarth()
{
	Random rand=new Random();
	int x,y;
	boolean eligible=false;
	int[] tobereturned=new int[2];
	while(!eligible)
	{
		x=rand.nextInt(53)+1;
		y=rand.nextInt(23)+1;
		if(Gravity.gamefield[y][x]==':')
		{
			eligible=true;
			tobereturned[0]=x;
			tobereturned[1]=y;
		}
	}
	return tobereturned;
}
}

