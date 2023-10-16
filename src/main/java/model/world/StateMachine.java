package model.world;

import java.util.*;

public class StateMachine {

    private IState currentState;
    private Map<IState, Set<Transition>> transitions;
    private Set<Transition> currentTransitions;
    private Set<Transition> anyTransitions;

    public StateMachine(){
        this.transitions = new HashMap<>();
        this.currentTransitions = new HashSet<>();
        this.anyTransitions = new HashSet<>();
    }

    public void tick(){
        if(currentState != null) {
            Transition t = getTransition();
            if (t != null) {
                setState(t.to);
            }
            currentState.tick();
        }
    }

   public void setState(IState state){
        if(state == currentState)
            return;
        if(currentState != null)
            currentState.onExit();

        currentState = state;

       Set currTransitions = transitions.get(currentState);
        if(currTransitions != null){
            currentTransitions = currTransitions;
        }
        else {
            currentTransitions = new HashSet<>();
        }

        currentState.onEnter();
   }

    public void addTransition(IState fromState, IState toState, ICondition condition){
        if(fromState != null && toState != null && condition != null) {

            Transition transition = new Transition(toState, condition);

            if(transitions.get(fromState) == null){
                Set list = new HashSet<>();
                list.add(transition);
                transitions.put(fromState, list);
            } else {
                transitions.get(fromState).add(transition);
            }
        }
    }

    public void addAnyTransition(IState state, ICondition condition){
        if(state != null && condition != null)
            anyTransitions.add(new Transition(state, condition));
    }

    private class Transition
    {
        private IState to;
        private ICondition icondition;

        public Transition(IState to, ICondition condition)
        {
            this.to = to;
            this.icondition = condition;
        }
    }

    private Transition getTransition(){

        for(Transition t : anyTransitions){
            if(t.icondition.condition()){
                return t;
            }
        }

        for(Transition t : currentTransitions){
            if(t.icondition.condition()){
                return t;
            }
        }

        return null;
    }

}
