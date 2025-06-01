package com.aws.home.services.BillingAndCost;

import java.util.List;
import java.util.Map;

public interface CostExplorerServiceInterface {
    List<Map<String, String>> GetCostExplorerList(String start, String end);
}
