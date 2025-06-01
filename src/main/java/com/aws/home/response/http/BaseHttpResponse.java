package com.aws.home.response.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseHttpResponse {
    public String message;
    public int size;
    public Object data;

    public BaseHttpResponse(String message) {
        this.message = message;
    }

    public BaseHttpResponse(String message, Object data, int size) {
        this.message = message;
        this.data = data;
        this.size = size;
    }

    public static BaseHttpResponse Ok(Object data){
        if(data.getClass().equals(ArrayList.class)){
            return new BaseHttpResponse("Action result: Success !", data, ((ArrayList<?>) data).size());

        }else{
            return new BaseHttpResponse("Action result: Success !", data, 0);
        }
    }

    public static BaseHttpResponse Fail(){
        return new BaseHttpResponse("Action result: Fail !");
    }

    public static BaseHttpResponse BaseSuccess(){
        List<Map<String, String>> data = new ArrayList<>();

        return BaseHttpResponse.Ok(data);
    }
}
