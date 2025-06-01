package com.aws.home.services.BillingAndCost;

import software.amazon.awssdk.services.costexplorer.CostExplorerClient;

import java.util.List;
import java.util.Map;

public class CostExplorerServiceImplementation implements CostExplorerServiceInterface{

    private final CostExplorerClient costClient;

    public CostExplorerServiceImplementation(CostExplorerClient costClient) {
        this.costClient = costClient;
    }

    @Override
    public List<Map<String, String>> GetCostExplorerList() {
        return List.of();
    }
}
