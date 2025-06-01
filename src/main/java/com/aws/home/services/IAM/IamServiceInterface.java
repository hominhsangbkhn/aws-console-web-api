package com.aws.home.services.IAM;


import java.util.List;
import java.util.Map;

public interface IamServiceInterface {
    List<Map<String, String>> GetListPolicies();

    List<Map<String, String>> GetListAttachedPolicies();
}
