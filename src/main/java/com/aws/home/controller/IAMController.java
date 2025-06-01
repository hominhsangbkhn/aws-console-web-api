package com.aws.home.controller;

import com.aws.home.response.http.BaseHttpResponse;
import com.aws.home.services.IAM.IamServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/iam")
public class IAMController {

    private final IamServiceInterface iamService;

    public IAMController(IamServiceInterface iamService) {
        this.iamService = iamService;
    }

    @GetMapping("/get-aws-polices")
    public BaseHttpResponse GetPolicy(){
        List<Map<String, String>> rs = iamService.GetListPolicies();
        return BaseHttpResponse.Ok(rs);
    }

    @GetMapping("/get-attached-user-polices")
    public BaseHttpResponse GetAttachedUserPolicy(){
        List<Map<String, String>> data = iamService.GetListAttachedPolicies();
        return BaseHttpResponse.Ok(data);
    }

}
