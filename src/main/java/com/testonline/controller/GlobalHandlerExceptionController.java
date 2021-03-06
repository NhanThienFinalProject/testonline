//package com.testonline.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//@ControllerAdvice
//public class GlobalHandlerExceptionController {
//
//    public static final String DEFAULT_ERROR_VIEW = "web/404";
//
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
//            throw e;
//        }
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
//    }
//}
