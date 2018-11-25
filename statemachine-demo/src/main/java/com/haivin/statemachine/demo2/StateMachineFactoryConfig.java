package com.haivin.statemachine.demo2;

import com.haivin.statemachine.demo1.CustomerAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.StateData;
import org.springframework.statemachine.config.model.TransitionData;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.List;

/**
 * @ClassName stateMachineFactoryConfig
 * @Description TODO
 * @Author zhouran
 * @Date 2018/11/25 1:20 PM
 * @Version 1.0
 **/

public abstract class StateMachineFactoryConfig extends StateMachineConfigurerAdapter<String, String> {
    @Autowired
    Custom2StateMachineModelFactory custom2StateMachineModelFactory;

    @Autowired
    CustomerAction customerAction;

    @Override
    public void configure(StateMachineModelConfigurer model) throws Exception {
        custom2StateMachineModelFactory.setConfigIniter((List<StateData<String, String>> stateDatas,
                                                         List<TransitionData<String, String>> transitionDatas) -> {
            stateDatas.clear();
            transitionDatas.clear();
            addStates(stateDatas);
            addTransitions(transitionDatas);
        });
        model.withModel().factory(custom2StateMachineModelFactory);
    }


    @Bean
    public StateMachineListener<String, String> listener() {
        return new StateMachineListenerAdapter<String, String>() {
            @Override
            public void stateChanged(State<String, String> from, State<String, String> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }

    public abstract void addStates(List<StateData<String, String>> stateDatas);

    public abstract void addTransitions(List<TransitionData<String, String>> transitionDatas);
}
