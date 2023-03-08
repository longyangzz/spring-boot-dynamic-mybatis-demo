package com.yhyr.mybatis.dynamic.config;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yhyr.mybatis.dynamic.datasource.DataSourceContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class DataSourceSwitchInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在请求处理之前进行调用（Controller方法调用之前）
        System.out.println("请求路径：" + request.getRequestURI());

        HttpServletRequest sRequest = (HttpServletRequest) request;
        //！ 获取用户信息，根据用户信息切换数据源
        //获取当前登录用户
        String authToken = sRequest.getHeader("Authorization");
        String userNmae = "";
        if( authToken != null &&  authToken.contains("Token")) {

            //! 截取去掉前边的Token，剩余的制为目标值
            //扩展，支持Token前传入数据源，避免登录
            //按token分割，分割后数组大小为2.   如果token前是空，则数组0为空，数组1为token值
            String[] tokenStrArray = authToken.split("Token");

            if(tokenStrArray.length == 2){
                String withinputDatasource = tokenStrArray[0];

                String aimauthToken = tokenStrArray[1];
                userNmae = praseJWTForName(aimauthToken);
                //判断是否指定了数据源
                if(withinputDatasource.isEmpty()){
//                    String  datasource= (String)DataSourceContextHolder.getDBType(userNmae);
//                    if(datasource != null && !datasource.isEmpty() ){
//                        DataSourceContextHolder.setDBType(withinputDatasource);
//                    }
                }else{
                    DataSourceContextHolder.setDBType(withinputDatasource);
                }

            }

        }

        return true; //如果返回false，请求将会被拦截
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于清理资源等工作）
    }

    public static String praseJWTForName(String tocken) {
        //String tocken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJiZWlqaW5nIn0.NFC7a5W2nyGcvl69lVgeW1DGwTPfHVUrAbxwlZqOqfs";
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(tocken);
            String username  = jwt.getIssuer();

            return username;
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
        }

        return "";
    }
}
