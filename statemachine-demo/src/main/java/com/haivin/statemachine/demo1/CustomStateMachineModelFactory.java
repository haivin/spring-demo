package com.haivin.statemachine.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Component
public class CustomStateMachineModelFactory implements StateMachineModelFactory<String, String> {

    private static ThreadLocal<String> code = ThreadLocal.withInitial(() -> "");
    @Autowired
    private CustomerAction customerAction;

    @Override
    public StateMachineModel<String, String> build() {
        ConfigurationData<String, String> configurationData = new ConfigurationData<>();
        Collection<StateData<String, String>> stateDatas = new ArrayList<>();
        if(code.get().equals("E2")){
            StateData<String, String> stateData1 = new StateData<>("S11", true);
            StateData<String, String> stateData2 = new StateData<>("S21");
            StateData<String, String> stateData3 = new StateData<>("S31");
            List<Action<String,String>> actions = new ArrayList<>();
            actions.add(customerAction);
            stateData2.setStateActions(actions);
            stateDatas.add(stateData1);
            stateDatas.add(stateData2);
            stateDatas.add(stateData3);
        }else{
            StateData<String, String> stateData1 = new StateData<>("S1", true);
            StateData<String, String> stateData2 = new StateData<>("S2");
            StateData<String, String> stateData3 = new StateData<>("S3");
//            stateData1.setInitialAction(customerAction);
//            stateData2.setInitialAction(customerAction);
//            stateData3.setInitialAction(customerAction);
            List<Action<String,String>> actions = new ArrayList<>();
            actions.add(customerAction);
            stateData1.setStateActions(actions);
            stateData2.setStateActions(actions);
            stateData3.setStateActions(actions);
            stateDatas.add(stateData1);
            stateDatas.add(stateData2);
            stateDatas.add(stateData3);
        }

        StatesData<String, String> statesData = new StatesData<>(stateDatas);
        Collection<TransitionData<String, String>> transitionData = new ArrayList<>();
        if(code.get().equals("E2")) {
            transitionData.add(new TransitionData<String, String>("S11", "S21", "E21"));
            transitionData.add(new TransitionData<String, String>("S21", "S31", "E22"));
        }else {
            transitionData.add(new TransitionData<String, String>("S1", "S2", "E11"));
            transitionData.add(new TransitionData<String, String>("S2", "S3", "E12"));
        }
        TransitionsData<String, String> transitionsData = new TransitionsData<>(transitionData);
        StateMachineModel<String, String> stateMachineModel = new DefaultStateMachineModel<String, String>(configurationData,
                statesData, transitionsData);
        return stateMachineModel;
    }

    @Override
    public StateMachineModel<String, String> build(String machineId) {
        return build();
    }

    public static String getCode() {
        return code.get();
    }

    public static void setCode(String threadLocal) {
        CustomStateMachineModelFactory.code.set(threadLocal);
    }
}