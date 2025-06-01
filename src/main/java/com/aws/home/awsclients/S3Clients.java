package com.aws.home.awsclients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Clients {

    @Bean("ap-southeast-1")
    public S3Client AsiaPacificSouthEastIamClient1(){
        Region region = Region.AP_SOUTHEAST_1;

        return S3Client.builder()
                .region(region)
                .build();
    }

    @Bean("ap-southeast-2")
    public S3Client AsiaPacificSouthEastIamClient2(){
        Region region = Region.AP_SOUTHEAST_2;

        return S3Client.builder()
                .region(region)
                .build();
    }

}
