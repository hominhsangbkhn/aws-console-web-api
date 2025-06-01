package com.aws.home.controller;

import com.aws.home.response.http.BaseHttpResponse;
import com.aws.home.services.BillingAndCost.BillingServiceInterface;
import com.aws.home.services.BillingAndCost.CostExplorerServiceInterface;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/billing")
public class BillingAndCostController {

    private final BillingServiceInterface billService;
    private final CostExplorerServiceInterface costService;

    public BillingAndCostController(BillingServiceInterface billService, CostExplorerServiceInterface costService) {
        this.billService = billService;
        this.costService = costService;
    }

    @GetMapping("/get-bill-list")
    public BaseHttpResponse GetBillList(){
        List<Map<String, String>> data = billService.GetBillingList();
        return BaseHttpResponse.Ok(data);
    }

    @GetMapping("/get-cost-list")
    public BaseHttpResponse GetCostList(@RequestParam @Schema(example = "2025-05-01") String start, @RequestParam @Schema(example = "2025-05-31") String end){
        try{
            List<Map<String, String>> data = costService.GetCostExplorerList(start, end);
            return BaseHttpResponse.Ok(data);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  BaseHttpResponse.Fail();
        }
    }
}
