package com.example.parking;

import java.util.Map;
import java.util.Objects;

public class FlatRatePricingPolicy implements PricingPolicy{
    private final Map<SlotType, Integer> rates;

    public FlatRatePricingPolicy(Map<SlotType, Integer> rates)
    {
        this.rates = Objects.requireNonNull(rates, "rates map must not be null");
    
    }

    @Override
    public int ratePerHour(SlotType slotType)
    {
        Integer rate = rates.get(slotType);
        if (rate == null)
    {
            throw new IllegalArgumentException("No rate configured for slot type: " + slotType);
        
        }
        
        return rate;
    }
}
