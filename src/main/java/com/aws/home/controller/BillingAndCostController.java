package com.aws.home.controller;

import com.aws.home.response.http.BaseHttpResponse;
import com.aws.home.services.BillingAndCost.BillingServiceInterface;
import com.aws.home.services.BillingAndCost.CostExplorerServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/billing")
public class BillingAndCostController {

    private final BillingServiceInterface billService;
    private final CostExplorerServiceInterface costService;

    public BillingAndCostController(BillingServiceInterface billService) {
        this.billService = billService;
    }

    @GetMapping("/get-bill-list")
    public BaseHttpResponse GetBillList(){
        List<Map<String, String>> data = billService.GetBillingList();
        return BaseHttpResponse.Ok(data);
    }

    @GetMapping("/get-cost-list")
    public BaseHttpResponse GetCostList(){
        List<Map<String, String>> data = costService.GetCostExplorerList();
        return BaseHttpResponse.Ok(data);
    }
}
