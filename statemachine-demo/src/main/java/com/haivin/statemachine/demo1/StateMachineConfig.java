package com.haivin.statemachine.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.ArrayList;
import java.util.List;

//@Configuration
//@EnableStateMachineFactory
public class StateMachineConfig
        extends StateMachineConfigurerAdapter<String, String> {
    @Autowired
    CustomStateMachineModelFactory customStateMachineModelFactory;

    /*@Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(false)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {
        states
                .withStates()
                .initial(States.SI.name())
                .states(new HashSet<String>(States.toList()));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.SI.name()).target(States.S1.name()).event(Events.E1.name())
                .and()
                .withExternal()
                .source(States.S1.name()).target(States.S2.name()).event(Events.E2.name());
    }*/

    @Override
    public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
        model
                .withModel()
                .factory(customStateMachineModelFactory);
    }

    /*@Bean
    public StateMachineModelFactory<String, String> modelFactory() {
        return new CustomStateMachineModelFactory();
    }*/



    @Bean
    public StateMachineListener<String, String> listener() {
        return new StateMachineListenerAdapter<String, String>() {
            @Override
            public void stateChanged(State<String, String> from, State<String, String> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }

    public enum States {
        SI, S1, S2;

        public static List toList(){
            List<String> list = new ArrayList<>();
            for (States ele :States.values()) {
                list.add(ele.name());
            }
            return list;
        }
    }

    public enum Events {
        E1, E2
    }
}