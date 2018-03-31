package com.topcoder.problems.wed091013;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//http://community.topcoder.com/stat?c=problem_statement&pm=93&rd=3009
public class FlightConection {

    public Set<String> flightSet = new HashSet<String>();
    public int result = 0;
    public int getMinimum(String[] param0, String param1, String param2){
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        
        for(String param : param0){
            String[] str = param.split(",");
            String source = str[0];
            String destination = str[1];
            if (map.get(source)== null){
                Set<String> set= new HashSet<String>();
                set.add(destination.trim());
                map.put(source, set);
            } else {
                Set<String> set = map.get(source);
                set.add(destination.trim());
            }
        }
        getMinimumUtil(map, param1, param2);
        return -1;
    }
    private void getMinimumUtil(Map<String, Set<String>> map, String source, String destination) {
        if (source.equals(destination)){
            if (result > 0 && flightSet.size() <= result)
                result = flightSet.size();
            else if (result == 0){
                result = flightSet.size();
            }                
            return;
        } else {
            if (!flightSet.contains(source)){
                flightSet.add(source);
                Set<String> nextFlights = map.get(source);   
                if (nextFlights!= null){
                    for(String flight : nextFlights){
                        getMinimumUtil(map, flight, destination);
                    }
                }
                flightSet.remove(source);
            }
        }
        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //char c = 'A';
        //System.out.println((int)c);
//        String[] param0 = {"T, B", "O, F", "D, Y", "F, K", "K, C", "V, N", "B, O"};
//        String source = "T";
//        String destinatoin = "C";
        String[] param0 = {"Hartford, New-York", "New-York, Atlanta", "New-York, Miami", "Miami, Seattle", "Hartford, New-York", "Seattle, Juan", "Atlanta, Juan"};
        String source = "Hartford";
        String destinatoin = "Juan";
        FlightConection flightConection = new FlightConection();
        flightConection.getMinimum(param0, source, destinatoin);
        System.out.println(flightConection.result);

    }

}
