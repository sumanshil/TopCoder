package com.geeksforgeeks.array;

public class VisitPetrolPump {
    static class PetrolPump{
    	public PetrolPump(int i, int j) {
			this.petrol = i;
			this.distance = j;
		}
		int petrol;
    	int distance;
    	
    }
    
    static PetrolPump[] pumps = new PetrolPump[]{new PetrolPump(4,6),new PetrolPump(6,5),new PetrolPump(7,3),new PetrolPump(4,5)};
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int curr_val = 0;
		int start = 0;
		int end = 1;
		
		curr_val += pumps[start].petrol- pumps[start].distance;
	    while (start != end || curr_val < 0){
	    	while(curr_val <0 && start != end){
	    		curr_val -= pumps[start].petrol - pumps[start].distance;
	    		start = (start+1)%pumps.length;
	    		if (start == 0){
	    			System.out.println("No Solution");
	    			break;
	    		}
	    	}
	    	curr_val += pumps[end].petrol - pumps[end].distance;
	    	end = (end+1)%pumps.length;
	    }
		System.out.println("Start position "+ start);
		
		
		
		
		

	}

}
