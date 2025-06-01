package com.aws.home.services.IAM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.*;

import java.util.*;

@Service
public class IamServiceImplementation implements IamServiceInterface {

    private final IamClient globalIamClient;

    public IamServiceImplementation(IamClient _globalIamClient) {
        globalIamClient = _globalIamClient;
    }

    @Override
    public List<Map<String, String>> GetListPolicies() {

        List<Map<String, String>> result = new ArrayList<>();

        ListPoliciesResponse response = globalIamClient.listPolicies();
        List<Policy> polList = response.policies();
        System.out.println(polList.size());
        for(Policy policy : polList){
            Map<String, String> item = new LinkedHashMap<>();

//            GetPolicyRequest getPolReq = GetPolicyRequest.builder().policyArn(policy.arn()).build();
//
//            GetPolicyResponse getPolRes = globalIamClient.getPolicy(getPolReq);
//
//            Policy polDetail = getPolRes.policy();

//            item.put("Policy managed by", getPolRes.getPo)
            item.put("Policy Name" , policy.policyName());
            item.put("Policy Arn" , policy.arn());
            item.put("Attachment Count", policy.attachmentCount().toString());

            result.add(item);
        }

        return result;
    }

    @Override
    public List<Map<String, String>> GetListAttachedPolicies() {
        List<Map<String,String>> data = new ArrayList<>();

        ListAttachedUserPoliciesRequest awsReq = ListAttachedUserPoliciesRequest.builder().userName("iam-web-api-user-1").build();
        ListAttachedUserPoliciesResponse awsRes = globalIamClient.listAttachedUserPolicies(awsReq);

        List<AttachedPolicy> polList = awsRes.attachedPolicies();

        for(AttachedPolicy pol : polList){
            Map<String, String> item = new LinkedHashMap<>();
            item.put("Policy Name", pol.policyName());
            item.put("ARN", pol.policyArn());

            data.add(item);
        }
        return data;
    }

    // Helper method to check if a policy is managed
    private static boolean isManagedPolicy(String policyArn) {
        // Check if the ARN starts with the AWS managed policy format
        return policyArn.startsWith("arn:aws:iam::aws:policy/");
    }
}
