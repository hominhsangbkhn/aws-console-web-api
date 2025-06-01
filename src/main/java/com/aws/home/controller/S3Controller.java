package com.aws.home.controller;

import com.aws.home.enums.RegionEnums;
import com.aws.home.response.http.BaseHttpResponse;
import com.aws.home.services.S3.S3ServiceInterface;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.regions.Region;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    private final S3ServiceInterface s3Service;


    public S3Controller(S3ServiceInterface s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/list-buckets")
    public BaseHttpResponse GetListBuckets(){
       try{
           List<Map<String, String>> data = s3Service.GetListBuckets();
           return BaseHttpResponse.Ok(data);
       }catch (Exception e){
           System.out.println(e.getMessage());
           return BaseHttpResponse.Fail();
       }
    }

    @PostMapping("/create-bucket")
    public BaseHttpResponse CreateNewBucket(@RequestParam RegionEnums reg, @RequestParam @Schema(example = "1-bucket-created-from-api") String bucketName){
        try{
            List<Map<String, String>> data = s3Service.CreateBucket(reg.name().toLowerCase().replace("_", "-"), bucketName);
            return BaseHttpResponse.Ok(data);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return BaseHttpResponse.Fail();
        }
    }
}
