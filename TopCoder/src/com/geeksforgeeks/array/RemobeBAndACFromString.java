package com.geeksforgeeks.array;

public class RemobeBAndACFromString {

	public static void removeBandAC(char[] str){
		int i = 0;
		int j = 0;
		boolean copy = false;
		int k = 0;
		char prevChar=' ' ;
		while(k < str.length){
			if (str[k] == 'a'){
				copy = false;				
			} else if (str[k] == 'b'){
				copy = false;
			} else if (str[k] == 'c'){
				if(prevChar =='a'){
					copy = false;						
				} else if (str[i-1] =='a'){
					copy = false;
				} else{
					copy = true;
				}
						
			} else{
				copy = true;
			}
			
			if (copy){
				if (prevChar =='a'){
					str[i] = prevChar;
					i++;
				}
				str[i] = str[k];
				i++;
			}
			prevChar = str[k];
			k++;
		}
		str[i] = '0';
	}
	
	enum State{ONE, TWO};
	public static void remove1(char[] str){
		State state = State.ONE;
		int j =0;
		for(int i = 0 ; i < str.length; i++){
			if (j>1 && str[j-1]=='c' && str[j-2]=='a'){
				j = j-2;
			}
			if(state == State.ONE && str[i] !='b' && str[i]!='a'){
				str[j] = str[i];
				j++;
			} 
			
			if (state == State.TWO && str[i] !='C' ){
				str[j]='a';
				j++;
				if (str[i] != 'a' && str[i]!='b'){
					str[j]=str[i];
					j++;					
				}
			}
			
			state = (str[i]=='a')?(State.TWO):(State.ONE);			
		}
		if (j>1 && str[j-1]=='c' && str[j-2]=='a'){
			j = j-2;
		}
		
		str[j]='0';
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        char[] arr = {'a','a','c','a','c'};
        remove1(arr);
        

	}

}
