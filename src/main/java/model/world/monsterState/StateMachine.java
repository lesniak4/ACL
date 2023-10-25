package model.world.monsterState;

import model.world.ICondition;

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
            Set<Transition> transitions = getTransition();
            for(Transition t : transitions){
                if(t.to != currentState) {
                    setState(t.to);
                    break;
                }
            }
            currentState.tick();
        }
    }

   public void setState(IState state){
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

    private Set<Transition> getTransition(){

        Set<Transition> transitions = new HashSet<>();

        for(Transition t : anyTransitions){
            if(t.icondition.condition()){
                transitions.add(t);
            }
        }

        for(Transition t : currentTransitions){
            if(t.icondition.condition()){
                transitions.add(t);
            }
        }

        return transitions;
    }

}
