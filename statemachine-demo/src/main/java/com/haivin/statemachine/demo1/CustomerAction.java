package com.haivin.statemachine.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * @ClassName CustomerAction
 * @Description TODO
 * @Author zhouran
 * @Date 2018/11/25 10:01 AM
 * @Version 1.0
 **/
@Component
public class CustomerAction implements Action {
    Logger LOGGER = LoggerFactory.getLogger(CustomerAction.class);

    @Override
    public void execute(StateContext stateContext) {
        LOGGER.info("---->customer action:{},source:{}",stateContext.getEvent(),stateContext.getSource());
    }
}
