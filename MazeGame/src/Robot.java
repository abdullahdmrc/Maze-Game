import java.util.Random;

public class Robot {
	private int counter;
	private int row;
	private int column;
	private int rnd;
	private boolean flag;
	private boolean emptyy;
	 public Robot() {
		 counter = 0;
		 flag = true;
	 }
	 
	 public char[][] createRobots(char[][] gameboard){
		 Random rnd = new Random();
		 while(flag) {
			 row = rnd.nextInt(23) + 1;
			 column = rnd.nextInt(53) + 1;
			 emptyy = gameboard[row][column] != '#' && gameboard[row][column] != ':' && gameboard[row][column] != 'O' && gameboard[row][column] != '1' && gameboard[row][column] != '2' && gameboard[row][column] != '3' && gameboard[row][column] != 'X' && gameboard[row][column] != 'P';
			 if(emptyy) {
				 gameboard[row][column] = 'X';
				 counter++;
			 }
			 if(counter == 7)
				 flag = false;
		 }
		 return gameboard;
	 }
	 
	 public char[][] robotMovements(char[][] gameboard){
		 Random random = new Random();
		 int counter=0;
		 boolean enc0=false,enc1=false,enc2=false,enc3=false;
		 for(int i = 1; i < 24; i++) {
			 for(int j = 1; j < 54; j++) {
				 if(gameboard[i][j] == 'X') {
					 flag = true;
					 while(flag) {
						 rnd = random.nextInt(4);
						 if(rnd == 0&&!enc0) {
							 enc0=true;
							 counter++;
							 if(gameboard[i][j + 1] != '#' && gameboard[i][j + 1] != ':' && gameboard[i][j + 1] != 'O' && gameboard[i][j + 1] != '1' && gameboard[i][j + 1] != '2' && gameboard[i][j + 1] != '3' && gameboard[i][j + 1] != 'X') {
								if(gameboard[i][j+1]=='P')
								{
									Gravity.robotEncounter=false;
								}
								 gameboard[i][j] = ' ';
								 gameboard[i][j + 1] = 'X';
								 helper.printGameArea(gameboard, Gravity.cn);
								 flag = false;
								 counter=0;
								 enc0=false;
								 enc1=false;
								 enc2=false;
								 enc3=false;
							 }
						 }
						 if(rnd == 1&&!enc1) {
							 enc1=true;
							 counter++;
							 if(gameboard[i][j - 1] != '#' && gameboard[i][j - 1] != ':' && gameboard[i][j - 1] != 'O' && gameboard[i][j - 1] != '1' && gameboard[i][j - 1] != '2' && gameboard[i][j - 1] != '3' && gameboard[i][j - 1] != 'X') {
								 if(gameboard[i][j-1]=='P')
									{
									 Gravity.robotEncounter=false;
									}
								 gameboard[i][j] = ' ';
								 gameboard[i][j - 1] = 'X';
								 helper.printGameArea(gameboard, Gravity.cn);
								 flag = false;
								 counter=0;
								 enc0=false;
								 enc1=false;
								 enc2=false;
								 enc3=false;
							 }
						 }
						 if(rnd == 2&&!enc2) {
							 enc2=true;
							 counter++;
							 if(gameboard[i + 1][j] != '#' && gameboard[i + 1][j] != ':' && gameboard[i + 1][j] != 'O' && gameboard[i + 1][j] != '1' && gameboard[i + 1][j] != '2' && gameboard[i + 1][j] != '3' && gameboard[i + 1][j] != 'X') {
								 if(gameboard[i+1][j]=='P')
									{
									 Gravity.robotEncounter=false;
									}
								 gameboard[i][j] = ' ';
								 gameboard[i + 1][j] = 'X';
								 helper.printGameArea(gameboard, Gravity.cn);
								 flag = false;
								 counter=0;
								 enc0=false;
								 enc1=false;
								 enc2=false;
								 enc3=false;
							 }
						 }
						 if(rnd == 3&&!enc3) {
							 enc3=true;
							 counter++;
							 if(gameboard[i - 1][j] != '#' && gameboard[i - 1][j] != ':' && gameboard[i - 1][j] != 'O' && gameboard[i - 1][j] != '1' && gameboard[i - 1][j] != '2' && gameboard[i - 1][j] != '3' && gameboard[i - 1][j] != 'X') {
								 if(gameboard[i-1][j]=='P')
									{
										Gravity.robotEncounter=false;
									}
								 gameboard[i][j] = ' ';
								 gameboard[i - 1][j] = 'X';
								 helper.printGameArea(gameboard, Gravity.cn);
								 flag = false;
								 counter=0;
								 enc0=false;
								 enc1=false;
								 enc2=false;
								 enc3=false;
							 }
						 }
						 if(counter==4)
						 {
							 enc0=false;
							 enc1=false;
							 enc2=false;
							 enc3=false;
							 flag=false;
							 counter=0;
						 }
					 }
				 }
			 }
		 }
		 return gameboard;
	 }
}
