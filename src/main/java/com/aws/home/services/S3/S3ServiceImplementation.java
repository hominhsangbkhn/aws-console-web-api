package com.aws.home.services.S3;

import com.aws.home.constants.BaseConstants;
import com.aws.home.response.http.BaseHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class S3ServiceImplementation implements S3ServiceInterface{

    private final ApplicationContext context;

    public S3ServiceImplementation(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public List<Map<String, String>> GetListBuckets() {
        List<Map<String, String>> rs = new ArrayList<>();

        for(Region reg: BaseConstants.LIST_REGIONS){
            S3Client client = (S3Client) context.getBean(reg.toString());
            Map<String, String> bucketItems = new LinkedHashMap<>();

            ListBucketsRequest awsReq = ListBucketsRequest.builder().bucketRegion(reg.id()).build();

            ListBucketsResponse awsRes = client.listBuckets(awsReq);

            for(Bucket bk : awsRes.buckets()){
                bucketItems.put("Bucket name" , bk.name());
                bucketItems.put("Region", bk.bucketRegion());
            }

            if(bucketItems.values().isEmpty()) continue;

            rs.add(bucketItems);
        }

        return rs;
    }

    public List<Map<String, String>> CreateBucket(String reg, String bucketName){
        List<Map<String, String>> res = new ArrayList<Map<String, String>>();

        S3Client s3Client = (S3Client) context.getBean(reg);

        S3Waiter s3Waiter = s3Client.waiter();
        CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();

        s3Client.createBucket(bucketRequest);
        HeadBucketRequest bucketRequestWait = HeadBucketRequest.builder()
                .bucket(bucketName)
                .build();

        // Wait until the bucket is created and print out the response.
        WaiterResponse<HeadBucketResponse> waiterResponse = s3Waiter.waitUntilBucketExists(bucketRequestWait);
        waiterResponse.matched().response().ifPresent(System.out::println);

        Map<String, String> data = new LinkedHashMap<>();

        data.put("Created Bucket Name" , bucketName);
        data.put("Created Bucket Region" , Region.of(reg).id());

        System.out.println(bucketName + " is ready");

        res.add(data);
        return res;
    }
}
