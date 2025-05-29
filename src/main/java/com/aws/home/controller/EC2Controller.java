package com.aws.home.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2AsyncClient;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/ec2")
public class EC2Controller {

    @GetMapping("/instance")
    public String DescribeInstance(){
        Region region = Region.US_EAST_1;
        Ec2Client ec2 = Ec2Client.builder()
                .region(region)
                .build();

        describeEC2Instances(ec2);
        ec2.close();

        return "hi";

    }

    public static void describeEC2Instances(Ec2Client ec2) {
        String nextToken = null;
        try {
            do {
                DescribeInstancesRequest request = DescribeInstancesRequest.builder().maxResults(6).nextToken(nextToken).build();
                DescribeInstancesResponse response = ec2.describeInstances(request);
                for (Reservation reservation : response.reservations()) {
                    for (Instance instance : reservation.instances()) {
                        System.out.println("Instance Id is " + instance.instanceId());
                        System.out.println("Image id is " + instance.imageId());
                        System.out.println("Instance type is " + instance.instanceType());
                        System.out.println("Instance state name is " + instance.state().name());
                        System.out.println("monitoring information is " + instance.monitoring().state());
                    }
                }
                nextToken = response.nextToken();
            } while (nextToken != null);

        } catch (Ec2Exception e) {
            System.err.println(e.awsErrorDetails().errorCode());
            System.exit(1);
        }
    }

    @GetMapping("/accounts")
    public String GetEc2Instances(){
        Ec2AsyncClient ec2AsyncClient = Ec2AsyncClient.builder()
                .region(Region.US_EAST_1)
                .build();

        try {
            CompletableFuture<DescribeAccountAttributesResponse> future = describeEC2AccountAsync(ec2AsyncClient);
            future.join();
            System.out.println("EC2 Account attributes described successfully.");
        } catch (RuntimeException rte) {
            System.err.println("An exception occurred: " + (rte.getCause() != null ? rte.getCause().getMessage() : rte.getMessage()));
        }

        return "hello";
    }

    /**
     * Describes the EC2 account attributes asynchronously.
     *
     * @param ec2AsyncClient the EC2 asynchronous client to use for the operation
     * @return a {@link CompletableFuture} containing the {@link DescribeAccountAttributesResponse} with the account attributes
     */
    public static CompletableFuture<DescribeAccountAttributesResponse> describeEC2AccountAsync(Ec2AsyncClient ec2AsyncClient) {
        CompletableFuture<DescribeAccountAttributesResponse> response = ec2AsyncClient.describeAccountAttributes();
        return response.whenComplete((accountResults, ex) -> {
            if (ex != null) {
                // Handle the exception by throwing a RuntimeException.
                throw new RuntimeException("Failed to describe EC2 account attributes.", ex);
            } else if (accountResults == null || accountResults.accountAttributes().isEmpty()) {
                // Throw an exception if the response is null or no account attributes are found.
                throw new RuntimeException("No account attributes found.");
            } else {
                // Process the response if no exception occurred.
                accountResults.accountAttributes().forEach(attribute -> {
                    System.out.println("\nThe name of the attribute is " + attribute.attributeName());
                    attribute.attributeValues().forEach(
                            myValue -> System.out.println("The value of the attribute is " + myValue.attributeValue()));
                });
            }
        });
    }
}
