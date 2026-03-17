package com.studyumbackend.common.aop;

import org.aspectj.lang.annotation.Pointcut;


public class PointcutBundle {

    @Pointcut("execution(* com.studyumbackend..*Controller*.*(..))")
    public void controllerPointCut(){
    }


    @Pointcut("execution(* com.studyumbackend..*ServiceImpl*.*(..))")
    public void serviceImplPointCut(){

    }

}
