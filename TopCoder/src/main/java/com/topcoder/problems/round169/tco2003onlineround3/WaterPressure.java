package com.topcoder.problems.round169.tco2003onlineround3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sshil on 12/17/2015.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1907&rd=4704
public class WaterPressure {
	static class SquarePosition {
		private int x;
		private int y;
		public SquarePosition(int x, int y){
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

	}

	int[][] matrix = null;
	boolean[][] filled = null;

	public int howLong(String[] layout) {
		matrix = new int[layout.length][layout[0].length()];
		int emptySlots = fillMatrixValueAndReturnEmptySlotNumber(layout);
		filled = new boolean[layout.length][layout[0].length()];
		filled[0][0] = true;
		List<SquarePosition> filledPosition = new ArrayList<>();
		int secondsElapsed = 0;
		int totalWater  = 2;
		filledPosition.add(new SquarePosition(0, 0));
		float pressure = 2;
		int loopCount = 0;
		while(true) {
			if (loopCount++ == 100000){
				break;
			}
			findAndFillNewSlots( pressure, filledPosition);
			if (emptySlots+1 == filledPosition.size()) {
				break;
			}
			totalWater++;
			secondsElapsed++;
			pressure = (float)totalWater / (float)filledPosition.size();
		}
		return emptySlots+1 == filledPosition.size() ? secondsElapsed : -1;
	}

	private int fillMatrixValueAndReturnEmptySlotNumber(String[] layout) {
		int numberOfEmptySlots = 0;
		for (int i = 0 ; i < layout.length ; i++){
			for ( int j = 0 ; j < layout[i].length() ; j++){
				if (layout[i].charAt(j) != 'X' && layout[i].charAt(j) != '.'){
					int value = Integer.parseInt(layout[i].charAt(j)+"");
					matrix[i][j] = value;
					numberOfEmptySlots++;
				} else {
					matrix[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		return  numberOfEmptySlots;
	}

	private void findAndFillNewSlots(float pressure, List<SquarePosition> position) {
		List<SquarePosition> currentList = new ArrayList<>(position);
		for (SquarePosition squarePosition : currentList) {
			findAndFillAdjacentPositions(squarePosition, pressure, position);
		}
	}

	private void findAndFillAdjacentPositions(SquarePosition squarePosition,
											  float pressure,
											  List<SquarePosition> alreadyFilled) {
		for ( int i  = -1 ; i <= 1 ; i++ ) {
			for ( int j = -1 ; j <=1 ; j++) {
				if ((i+j) == 1 || (i+j) == -1){
					int newX = squarePosition.getX()+i;
					int newY = squarePosition.getY()+j;
					if (isValid(newX, newY)
						&& matrix[newX][newY] < pressure){
						filled[newX][newY] = true;
						alreadyFilled.add(new SquarePosition(newX, newY));
					}
				}
			}
		}
	}

	private boolean isValid(int newX, int newY) {
		return  newX>=0 && newX < matrix.length && newY >=0 && newY < matrix[0].length && !filled[newX][newY];
	}

	public static void main(String[] args) {
		String[] layout = {".85773817518159249980260123498780838839X6384717463",
			"242194445126058X5X93973323184X3X2747X842171X286218",
			"598X67822897X5X61920060240170X31256497102692827551",
			"54X36408X2548801X136636X63X9233X5949346X2274580162",
			"5108021354X77X4477509918743895XXX159760734682X9115",
			"50675439X0922X79916947371X01901217614357X0397201X3",
			"27186118593416001273104X29X76977141883369859598888",
			"80914X110594111X436841522027X668157791200228638293",
			"940105447111X78X25011577574123524X04XXX51614492051",
			"8804363635177X8246382862576X601870X74X139993345821",
			"X48877009143171494700XX3X16138573259477742520850X9",
			"1612588394913980186365912312794180464979928X986475",
			"595353627322252222412X883094875X522435782213598237",
			"17391130462739808923430X258255508957X4539278411137",
			"161196X087X1959308152303433460329X6588920868810551",
			"5X05897317154867X286045721X246725361349XX31742455X",
			"X6356984801056259X735653998127X568670314628X485224",
			"9320088X74859675156365X779X1X326767X79844419358X2X",
			"501055497X39933X951356690965X4X0844917186293X57985",
			"33413302X1903266448066612423X8038586XX5638903X7517",
			"2976X251488X07X87464805678010167X32X54795816434863",
			"18X8XX869283086X940798825X85739462998X3X3834428505",
			"2555044879X7309586526260751345349591446476X7010X13",
			"3233684019X20X770513442033X6950060849326X814045XX1",
			"7378853595549545X9334157X55988471X21X3295375894550",
			"7X9037779323X31807337002826035684828290777975X01X4",
			"88X7X979X7X39506209854X7415XX8999X2253016X25035787",
			"7683015X17268X16694392X6X101441658747937X198485622",
			"1624X697868860600X030X577356X121482831384673XX7334",
			"4662712538X13X4168850436576272047034855X5933218977",
			"363X88X712383950892X775895613479X5287X01182X614158",
			"0944515500373672425X6066X2108X3247524582717066X178",
			"637664873632165059897471275828595X6527260271X84X40",
			"305512X0X86260235207058X57705027964497905850617962",
			"935138076111582XX0369X62229X178X921961753325422758",
			"9987594X029815548549X858304X3181752985X5927X716348",
			"631347X74X23510X703201850X965X8803X5794257X7X32012",
			"597X2244071123X41X44196191328874040522673675346X00",
			"9501X65935X9375252X55199X261467XXX8118871789524X62",
			"732087503876417410X23X95740041509X58104X7543083124",
			"175978X56605686460653815X08585X89XX137855313405573",
			"51593131735080324921X2253417397314274XX231X5293926",
			"XX778362173333574X333953005142250501X79X21757X1264",
			"57350X7252505123850942501148X63526X106805090642546",
			"2331X884X085X068X18X612328531670865X09525594XX98X6",
			"6935X60327121102150643X49857600X77316813X362130791",
			"331X29776535751774789772426596X1961969905180339X10",
			"358X6622772195016X79558982X1024678439091439835013X",
			"305362575995391477X2744272460098891730534152558XX1",
			"28318306546365480X775289935065X4X48610794894231736"};
		int result = new WaterPressure().howLong(layout);
		System.out.println(result);
	}
}
