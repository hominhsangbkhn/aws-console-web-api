package com.aws.home.services.BillingAndCost;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.billing.BillingClient;
import software.amazon.awssdk.services.billing.model.ActiveTimeRange;
import software.amazon.awssdk.services.billing.model.BillingViewListElement;
import software.amazon.awssdk.services.billing.model.ListBillingViewsRequest;
import software.amazon.awssdk.services.billing.model.ListBillingViewsResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillingListImplementation implements BillingServiceInterface{

    private final BillingClient billingClient;

    public BillingListImplementation(BillingClient billingClient) {
        this.billingClient = billingClient;
    }

    @Override
    public List<Map<String, String>> GetBillingList() {

        List<Map<String, String>> data = new ArrayList<>();

        ActiveTimeRange actTime = ActiveTimeRange.builder().activeAfterInclusive(Instant.parse("2025-05-01T00:00:00Z")).activeBeforeInclusive(Instant.parse("2025-05-30T00:00:00Z")).build();
        ListBillingViewsRequest awsReq = ListBillingViewsRequest.builder().activeTimeRange(actTime).build();

        ListBillingViewsResponse awsRes = billingClient.listBillingViews(awsReq);

        List<BillingViewListElement> billLst = awsRes.billingViews();

        for(BillingViewListElement bill : billLst){
            Map<String, String> item = new LinkedHashMap<>();

            item.put("Bill Name" , bill.name());
            item.put("Bill Des" , bill.description());
            item.put("Bill data" , bill.toString());

            data.add(item);
        }


        return data;
    }
}
