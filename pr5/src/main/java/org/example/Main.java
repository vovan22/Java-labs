package org.example;

public class Main {
    public static void main(String[] args) {
        String input = "TTEST";
        FiniteStateMachine fsm = new FiniteStateMachine();

        for (char c : input.toCharArray()) {
            fsm.process(c);
            System.out.println("Processed '" + c + "', Current State: " + fsm.getCurrentState());
        }

        System.out.println("Final State: " + fsm.getCurrentState());
        System.out.println("Is 'TEST' found: " + fsm.isFinal());
    }
}
