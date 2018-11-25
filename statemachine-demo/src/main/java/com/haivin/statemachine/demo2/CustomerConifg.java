package com.haivin.statemachine.demo2;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.model.StateData;
import org.springframework.statemachine.config.model.TransitionData;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CustomerConifg
 * @Description TODO
 * @Author zhouran
 * @Date 2018/11/25 3:34 PM
 * @Version 1.0
 **/
@Configuration
@EnableStateMachineFactory
public class CustomerConifg extends StateMachineFactoryConfig {

    private static final ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(()->"");

    @Override
    public void addStates(List<StateData<String, String>> stateDatas) {
        if(getCode().equals("E1")){
            StateData<String, String> stateData1 = new StateData<>("S1", true);
            StateData<String, String> stateData2 = new StateData<>("S2");
            StateData<String, String> stateData3 = new StateData<>("S3");
            List<Action<String, String>> actions = new ArrayList<>();
            actions.add(customerAction);
            stateData2.setStateActions(actions);
            stateData3.setStateActions(actions);
            stateDatas.add(stateData1);
            stateDatas.add(stateData2);
            stateDatas.add(stateData3);
        }else if(getCode().equals("E2")){
            StateData<String, String> stateData1 = new StateData<>("S11", true);
            StateData<String, String> stateData2 = new StateData<>("S21");
            StateData<String, String> stateData3 = new StateData<>("S31");
            List<Action<String, String>> actions = new ArrayList<>();
            actions.add(customerAction);
            stateData2.setStateActions(actions);
            stateData3.setStateActions(actions);
            stateDatas.add(stateData1);
            stateDatas.add(stateData2);
            stateDatas.add(stateData3);
        }
    }

    @Override
    public void addTransitions(List<TransitionData<String, String>> transitionDatas) {

        if(getCode().equals("E1")){
            transitionDatas.add(new TransitionData<String, String>("S1", "S2", "E11"));
            transitionDatas.add(new TransitionData<String, String>("S2", "S3", "E12"));
        }else if(getCode().equals("E2")){
            transitionDatas.add(new TransitionData<String, String>("S11", "S21", "E21"));
            transitionDatas.add(new TransitionData<String, String>("S21", "S31", "E22"));
        }
    }

    public static String getCode() {
        return THREAD_LOCAL.get();
    }

    public static void setCode(String code) {
        THREAD_LOCAL.set(code);
    }



}
