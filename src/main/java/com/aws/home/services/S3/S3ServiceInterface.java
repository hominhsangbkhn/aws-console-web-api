package com.aws.home.services.S3;

import software.amazon.awssdk.regions.Region;

import java.util.List;
import java.util.Map;

public interface S3ServiceInterface {
    List<Map<String, String>> GetListBuckets();

    List<Map<String, String>> CreateBucket(String reg, String bucketName);
}
