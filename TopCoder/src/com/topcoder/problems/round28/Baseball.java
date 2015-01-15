package com.topcoder.problems.round28;


//http://community.topcoder.com/stat?c=problem_statement&pm=158&rd=3027
public class Baseball {	 
	 enum BaseType{
		 HOME,
		 ONE, 
		 TWO,
		 THREE
	 }
	 
	 interface PlayerReplacable{
		 public void replace(int player, int batsman, char action, int distanceToRun, int currentDistance, int roundCount);
	 }
	 static class Base implements PlayerReplacable {
		 BaseType type;
		 int currentPlayer;		 
		 Base next;
		 int roundCount;
		 public void replace(int player, int batsman, char action, int distanceToRun, int currentDistance, int roundCount) {
			if (type == BaseType.HOME){
				System.out.println("Player "+player +" gets a run");
				run[player-1]++;
				if (action != 'W'){
					System.out.println("Batsman "+batsman +" gets a RBI");
					rbi[batsman-1]++;
				}
				return;
			}
			if (type != BaseType.HOME && currentPlayer != 0){
			    next.replace(currentPlayer, batsman, action, distanceToRun, --distanceToRun, roundCount);
			    currentPlayer = 0;
			}
            if (currentDistance == 0 && player > 0){
				currentPlayer = player;
				this.roundCount = roundCount;
			} else {
            	next.replace(player, batsman, action, distanceToRun, --currentDistance, roundCount);
            }     
                      
		}	
	 }
	 
	 static Base root;
	 
	 static{
		 root = new Base();
		 root.type = BaseType.HOME;
		 
		 root.next = new Base();
		 root.next.type = BaseType.ONE;
		 
		 root.next.next = new Base();
		 root.next.next.type = BaseType.TWO;
		 
		 root.next.next.next = new Base();
		 root.next.next.next.type = BaseType.THREE;
		 
		 root.next.next.next.next = root;
		 
	 }
	 
	 int[] playerLocation = new int[4]; 
	 
	 static int[] hits = new int[10];
	 static int[] walks = new int[10];
	 static int[] run = new int[10];
	 static int[] rbi = new int[10];
	 
	 
	 public int[] stats(int player, String whatHappened){
		 int currentOutCount = 0;
		 for(int i = 0 ; i < whatHappened.length() ; i++){
			 System.out.println("Round no. "+(i+1));
			 int j = i;
			 if (i > 9){
				 j = j % 10;
			 }
			 char c= whatHappened.charAt(i);
			 if (c == 'O'){
				 currentOutCount++;
				 if (currentOutCount == 3){
					 clearTheBases();
					 currentOutCount = 0;
				 }
				 continue;
			 }
			 int distance = getDistance(c);
			 if (c == 'W'){
				 walks[j]++;
			 } else {
				 hits[j]++;
			 }
//			 if (c != 'W'){
//				 hits[j]++;
//			 }
			 if (distance > 0)
				 root.next.replace(j+1, j+1, c, distance, distance-1, i);			 
			 
			 checkOtherBases(root.next, i, j+1, distance,c);
			 
			 print();
			 System.out.println("============================");
		 }
		 
		 int[] result = new int[4];
		 result[0] = hits[player-1];
		 result[1] = run[player-1];
		 result[2] = rbi[player-1];
		 result[3] = walks[player-1];
		 return result;
	 }
	 
	 private void print(){
		 Base root1 = root.next;
		 while(root1 != root){
			 System.out.println("Current base "+ root1.type.toString());
			 System.out.println("Current Player "+ root1.currentPlayer);
			 System.out.println("Current round "+ root1.roundCount);
			 root1 = root1.next;
		 }		
	 }
	 
	 private void checkOtherBases(Base node, int roundCount, int batsman, int distance, char action){
		 while(node.type != BaseType.HOME){
			 if (node.roundCount < roundCount && node.currentPlayer != 0){
				 node.roundCount = roundCount;
				 int currentPlayer =node.currentPlayer;
				 node.currentPlayer = 0;
				 node.next.replace(currentPlayer, batsman, action, distance, --distance, roundCount);
			 }
			 node = node.next;
		 }
	 }
	 
	 private void clearTheBases() {
		root.currentPlayer = 0;
		Base t = root.next;
		while(t != root){
			t.currentPlayer = 0;
			t = t.next;
		}
		
	}

	private int getDistance(char c){
		 if (c == 'O')
			 return 0;
		 else if (c == 'H')
			 return 4;
		 else if (c == 'W')
			 return 1;
		 else
			 return c- 48;
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// failed case (1, "3W")
		//	4, "111HOW1OOW11H2"
		//	1, "21OOO"
		int[] result = new Baseball().stats(1, "3W");
		for(int i = 0 ; i < result.length ; i++){
			System.out.println(result[i]);
		}
		//System.out.println((int)'1');

	}

}
