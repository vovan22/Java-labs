package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FiniteStateMachineTest {

    @ParameterizedTest
    @CsvSource({
            "'abcTESTabc', F",
            "'abcTES', THREE",
            "'abcTET', S",
            "'TEST', F",
            "'abcTESTTEST', F",
            "'abcSOMETHING', S",
            "'TESTTEST', F",
            "'TESTES', F",
            "'TESTTTT', F",
            "'T', ONE",
            "'TE', TWO",
            "'TES', THREE",
            "'TESTX', F",
            "'TEXST', ONE",
            "'TESST', ONE",
            "'', S",
            "'TTTTT', ONE",
            "'TESTESTEST', F",
            "'ABCDEF', S",
            "'ABCTESTEFGTESTH', F"
    })
    void testFiniteStateMachine(String input, String expectedState) {
        FiniteStateMachine fsm = new FiniteStateMachine();

        for (char c : input.toCharArray()) {
            fsm.process(c);
        }

        assertEquals(FiniteStateMachine.State.valueOf(expectedState), fsm.getCurrentState());
    }
}
