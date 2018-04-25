package test;

import controller.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StateTest {
    private State state;
    @Before
    public void Init(){
        state=new State();
    }
    @Test
    public void shouldChangeStateToMailBoxMenu() {
        state=new State();
        state.setMailBoxMenu();
        assertEquals(state.isMailBoxMenu(),true);
    }
    @Test
    public void  shouldChangeStateToMessageMenu() {
        state.setMessageMenu();
        assertEquals(state.isMessageMenu(),true);
    }
    @Test
    public void  shouldChangeStateToChangeGreeting () {
        state.setChangeGreeting();
        assertEquals(state.isChangeGreeting(),true);
    }
    @Test
    public void  shouldChangeStateToChangePassCode() {
        state.setChangePassCode();
        assertEquals(state.isChangePassCode(),true);
    }
    @Test
    public void  shouldChangeStateToConnected() {
        state.setConnected();
        assertEquals(state.isConnected(),true);
    }
    @Test
    public void  shouldChangeStateToRecording() {
        state.setRecording();
        assertEquals(state.isRecording(),true);
    }
}
