package com.haivin.statemachine.demo2;

import org.springframework.statemachine.config.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Custom2StateMachineModelFactory implements StateMachineModelFactory<String, String> {


    private List<TransitionData<String,String>> transitionDatas = new ArrayList<>();
    private List<StateData<String,String>> stateDatas = new ArrayList<>();
    private ConfigIniter configIniter;
    @Override
    public StateMachineModel<String, String> build() {
        return build("");
    }

    @Override
    public StateMachineModel<String, String> build(String machineId) {
        ConfigurationData<String, String> configurationData = new ConfigurationData<>();
        if(configIniter != null){
            configIniter.init(stateDatas,transitionDatas);
        }
        StatesData<String, String> states = new StatesData<>(stateDatas);
        TransitionsData<String, String> transitions = new TransitionsData<>(transitionDatas);
        StateMachineModel<String, String> stateMachineModel = new DefaultStateMachineModel<String, String>(configurationData,
                states, transitions);
        return stateMachineModel;
    }

    public interface ConfigIniter{
        void init(List<StateData<String,String>> stateDatas,List<TransitionData<String,String>> transitionDatas);
    }

    public ConfigIniter getConfigIniter() {
        return configIniter;
    }

    public void setConfigIniter(ConfigIniter configIniter) {
        this.configIniter = configIniter;
    }
}