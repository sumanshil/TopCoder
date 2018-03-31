package com.topcoder.problems.round171;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by sshil on 11/2/16.
 */
public class CrossCountry {

    static class Group {
        private String identifier;
        private int totalParticipants;
        private int sixthParticipantRank;
        private int totalScore;
        private int sixthParticipantCount;

        public Group(String identifier){
            this.identifier = identifier;
        }

        private void handleParticipation(int rank) {
            if (totalParticipants >=6){
                return;
            }
            if ( totalParticipants == 5) {
                sixthParticipantRank = rank;
                sixthParticipantCount++;
            } else {
                totalScore += rank;
            }
            totalParticipants++;
        }

        private boolean isQualified(){
            return this.totalParticipants >= 5;
        }

        private int getTotalScore(){
            return totalScore;
        }

        private int getSixthParticipant(){
            return sixthParticipantCount;
        }

        private int getSixthParticipantRank(){
            return sixthParticipantRank;
        }

        private String getIdentifier(){
            return this.identifier;
        }
    }

    private Map<String, Group> map = new HashMap<>();

    public String scoreMeet(int numTeams, String finishOrder) {
        Comparator<Group> comparator = ( o1,  o2) -> {
          if (o1.getSixthParticipant() == 0 && o2.getSixthParticipant() == 1){
              return 1;
          } else if (o1.getSixthParticipant() == 1 && o2.getSixthParticipant() == 0){
              return -1;
          } else {
              return 0;
          }
        };

        AtomicInteger atomicInteger = new AtomicInteger(0);
        finishOrder
                .chars()
                .mapToObj(i -> (char)i+"" )
                .forEach(s -> this.handle(s,atomicInteger.incrementAndGet()));
        String result = map.entrySet()
                        .stream()
                        .map(g -> g.getValue())
                        .filter(g -> g.isQualified())
                        .sorted( Comparator.comparing(Group::getTotalScore)
                                .thenComparing (comparator)
                                .thenComparing(Group::getSixthParticipantRank)
                                .thenComparing(Group::getIdentifier))
                        .map(Group::getIdentifier)
                        .collect(Collectors.joining());
        return result;
    }

    public void handle(String str, int index){
        if (map.containsKey(str)){
            map.get(str).handleParticipation(index);
        } else {
            Group grp = new Group(str);
            grp.handleParticipation(index);
            map.put(str, grp);
        }
    }

    public static void main(String[] args) {
        int i = 2;
        String str = "BDHCEAJBIDBCCHGCBDJEBAAHEGAGGADHGIECJEHAEBDADJCDHG";
        String res = new CrossCountry().scoreMeet(2, str);
        System.out.println(res);
    }
}
