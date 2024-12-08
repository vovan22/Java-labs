package org.example;

public class FiniteStateMachine {
    public enum State {
        S, ONE, TWO, THREE, F
    }

    private State currentState;
    private boolean foundTest;

    public FiniteStateMachine() {
        this.currentState = State.S;
        this.foundTest = false;
    }

    public void reset() {
        this.currentState = State.S;
        this.foundTest = false;
    }

    public State process(char c) {
        if (foundTest) {
            return currentState;
        }

        switch (currentState) {
            case S:
                if (c == 'T') currentState = State.ONE;
                else currentState = State.S;
                break;
            case ONE:
                if (c == 'E') currentState = State.TWO;
                else currentState = State.S;
                break;
            case TWO:
                if (c == 'S') currentState = State.THREE;
                else currentState = State.S;
                break;
            case THREE:
                if (c == 'T') {
                    currentState = State.F;
                    foundTest = true;
                } else currentState = State.S;
                break;
            case F:
                break;
        }
        return currentState;
    }

    public boolean isFinal() {
        return foundTest;
    }

    public State getCurrentState() {
        return currentState;
    }
}
