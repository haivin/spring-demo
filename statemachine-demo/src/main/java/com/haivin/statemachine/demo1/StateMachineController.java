package com.haivin.statemachine.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName StateMachineController
 * @Description TODO
 * @Author zhouran
 * @Date 2018/11/18 5:30 PM
 * @Version 1.0
 **/

@RequestMapping("/demo1")
@Controller
public class StateMachineController {


//    @Resource(name = "stateMachineFactory")
    private StateMachineFactory<String,String> stringStringStateMachineFactory;
    @Autowired
    private StateMachineListener stateMachineListener;

    @RequestMapping("/test1")
    @ResponseBody
    public void demo1Test1(){
        CustomStateMachineModelFactory.setCode("E1");
        StateMachine stateMachine = stringStringStateMachineFactory.getStateMachine();
        stateMachine.addStateListener(stateMachineListener);
        stateMachine.start();
        stateMachine.sendEvent("E11");
        stateMachine.sendEvent("E12");
        stateMachine.stop();
    }

    @RequestMapping("/test2")
    @ResponseBody
    public void demo1Test2(){
        CustomStateMachineModelFactory.setCode("E2");
        StateMachine stateMachine = stringStringStateMachineFactory.getStateMachine();
        stateMachine.addStateListener(stateMachineListener);
        stateMachine.start();
        stateMachine.sendEvent("E21");
        stateMachine.sendEvent("E22");
        stateMachine.stop();
    }
}
