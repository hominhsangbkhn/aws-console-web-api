package com.aws.home.awsclients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.billing.BillingClient;
import software.amazon.awssdk.services.costexplorer.CostExplorerClient;
import software.amazon.awssdk.services.iam.IamClient;

@Configuration
public class GlobalClients {

    @Bean
    public IamClient GlobalIamClient(){
        Region region = Region.AWS_GLOBAL;

        return IamClient.builder()
                .region(region)
                .build();
    }

    @Bean
    public BillingClient GlobalBillingClient(){
        Region region = Region.AWS_GLOBAL;
        return BillingClient.builder()
                .region(region)
                .build();
    }

    @Bean
    public CostExplorerClient GlobalCostClient(){
        Region region = Region.AWS_GLOBAL;
        return CostExplorerClient.builder()
                .region(region)
                .build();
    }

}
