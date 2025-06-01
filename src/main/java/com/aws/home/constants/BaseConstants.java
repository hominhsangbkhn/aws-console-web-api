package com.aws.home.constants;

import software.amazon.awssdk.regions.Region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseConstants {

    public static final List<Region> LIST_REGIONS = new ArrayList<>(Arrays.asList(Region.AP_SOUTHEAST_1, Region.AP_SOUTHEAST_2));
}
