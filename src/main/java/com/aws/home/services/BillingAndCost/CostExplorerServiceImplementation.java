package com.aws.home.services.BillingAndCost;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.costexplorer.CostExplorerClient;
import software.amazon.awssdk.services.costexplorer.model.DateInterval;
import software.amazon.awssdk.services.costexplorer.model.GetCostAndUsageRequest;
import software.amazon.awssdk.services.costexplorer.model.GetCostAndUsageResponse;
import software.amazon.awssdk.services.costexplorer.model.ResultByTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CostExplorerServiceImplementation implements CostExplorerServiceInterface{

    private final CostExplorerClient costClient;

    public CostExplorerServiceImplementation(CostExplorerClient costClient) {
        this.costClient = costClient;
    }

    @Override
    public List<Map<String, String>> GetCostExplorerList(String start, String end) {

        List<Map<String, String>> data = new ArrayList<>();

        // Replace with your dates (format: YYYY-MM-DD)

        GetCostAndUsageRequest request = GetCostAndUsageRequest.builder()
                .timePeriod(DateInterval.builder().start(start).end(end).build())
                .granularity("MONTHLY") // or "DAILY"
                .metrics("UnblendedCost") // or ["BlendedCost", "AmortizedCost", etc.]
                .build();

        GetCostAndUsageResponse response = costClient.getCostAndUsage(request);

        for (ResultByTime result : response.resultsByTime()) {
            Map<String, String> item = new HashMap<>();
            item.put("Time period: ", result.timePeriod().start() + " to " + result.timePeriod().end());
            item.put("Total cost: ", result.total().get("UnblendedCost").amount() + " " +
                    result.total().get("UnblendedCost").unit());

            data.add(item);
        }

        return data;
    }
}
