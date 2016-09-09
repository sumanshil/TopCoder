package com.topcoder.problems.round162;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=1841&rd=4615
public class SMBus
{
    static class Master implements Comparable<Master>
    {
        String message;
        int    sequence;
        int    timeToTransmitAByte;
        int    messageIndex;
        public Master(String message,
                      int sequence,
                      int timeToTransmitAByte,
                      int messageIndex)
        {
            this.message = message;
            this.sequence = sequence;
            this.timeToTransmitAByte = timeToTransmitAByte;
            this.messageIndex = messageIndex;            
        }
        
        
        public Master()
        {
            
        }


        @Override
        public int compareTo(Master o)
        {
            return  (this.message.compareTo(o.message));
        }
        
    }
    public int transmitTime(String[] messages, int[] times)
    {
        // parse and store the elements
        List<Master> masterList = parseAndStoreTheElements(messages, times);
        // sort the elements
        Collections.sort(masterList);
        // create an array of arbitrator which has all the masters
        
        // for each elements in the list
        int totalTimeTaken = 0;
        for(int i = 0 ; i < masterList.size() ; i++)
        {
            Master master = masterList.get(i);
            // initialize arbitrators list with 
            List<Master> arbitrators 
                  = new LinkedList<Master>(masterList.subList(i, masterList.size()));
            
            for(int j = 0 ; j < master.message.length(); j++)
            {
                Integer maxTime = Integer.MIN_VALUE;
                List<Master>  masterWithMaxChar = new LinkedList<Master>();
                char currentChar = master.message.charAt(j);
                for(Master arbitrator : arbitrators)
                {
                    if (arbitrator.timeToTransmitAByte > maxTime)
                    {
                        maxTime = arbitrator.timeToTransmitAByte;
                    }
                    
                    if (arbitrator.message.charAt(j) > currentChar)
                    {
                        currentChar = arbitrator.message.charAt(j);
                        masterWithMaxChar.add( arbitrator);
                    }
                    else if (masterWithMaxChar.size() > 0 
                            && arbitrator.message.charAt(j) == currentChar)
                    {
                        masterWithMaxChar.add( arbitrator);
                    }
                }
                totalTimeTaken += maxTime;
                System.out.print(maxTime+" ");
                if (masterWithMaxChar.size() > 0)
                {
                    for(Master m : masterWithMaxChar)
                    {
                        arbitrators.remove(m);
                    }
                }
                    
            }
        }
        return totalTimeTaken;
        
        // get the next available element 
             
            // get its string
        
            // for each character in the string
        
            // compare character at same index in other elements
            // inOrder the highest char and store its reference
            // inOrder the highest time and its reference
            // time += time + highestTime
            //  remove the element with highest char from arbitrator list
    }
    
    
    private List<Master> parseAndStoreTheElements(String[] messages,
                                                  int[] times)
    {
        List<Master> list = new LinkedList<Master>();
        for(int i = 0 ; i < messages.length ; i++)
        {
            Master master = new Master();
            master.message = messages[i];
            master.timeToTransmitAByte = (times[i]);
            list.add(master);
        }
        return list;
    }


    public static void main(String[] args)
    {
        int result = new SMBus().transmitTime(new String[]{"0002019289019101039663222896280025447",
                "00201873554718989597528841094293294387326",
                "010699029378761", "0110118", "011143279122561420",
                "001046384966198", "00200570375817801109530240012",
                "0003163277589822", "01100240744590150136673219652442108",
                "012033596872642679096489479354", "0121059494098",
                "00001002303019117948961792176", "00216399923558", "02014",
                "00224999120803846121427603894967637446889670369",
                "01101009414735635151893037596051740080475886",
                "0000101211809647472761605153430927981533514",
                "176845042961739039392207791589",
                "02000047506929386333221966659552927385017901842706",
                "021001117450487502127241076595509511",
                "021010776262723557108100899515",
                "0210114830738951774606917781619656",
                "0211798433083855430", "00000005842153604632996228135814",
                "0001000766929248550736138555144997170272757787",
                "0001010247593342056062432721557",
                "01100004828618452515832113396660046634951",
                "0132559984733529872911444204991646138116334788",
                "0224149857349431864906523152249992",
                "00001142929552573133212195441932219",
                "0011090498947092002457447355036811372647896489218",
                "000001275414757476198997466", "00010014",
                "00111025861963467834393486017602553072649743",
                "000102085", "00120882661783539",
                "01100018938145727291185020",
                "01100191373790478446634214244459341793", "0001129060",
                "001111287431066271555393813846448",
                "011010160778408696098486370196313", "0002125146381515395"}, new int[] {42, 86, 47, 86, 32, 95, 2, 98, 17, 58, 31, 32, 85, 77,
                        87, 61, 1, 20, 15, 80, 20, 95, 55, 87, 52, 14, 55, 68,
                        2, 66, 67, 3, 28, 97, 100, 67, 65, 20, 28, 77, 93, 64});
        System.out.println(result);

    }

}
