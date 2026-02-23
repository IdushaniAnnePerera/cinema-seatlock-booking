package com.cinema.controller;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
  @Value("${app.stripe-secret}") private String stripeSecret;
  @Value("${app.frontend-url}") private String frontend;

  @PostMapping("/stripe/session")
  public Map<String,String> session(@RequestBody Map<String,Object> payload) throws Exception {
    Stripe.apiKey = stripeSecret;
    long amount = ((Number) payload.getOrDefault("amount", 1000)).longValue();
    var params = SessionCreateParams.builder()
      .setMode(SessionCreateParams.Mode.PAYMENT)
      .setSuccessUrl(frontend + "/checkout/success")
      .setCancelUrl(frontend + "/checkout/cancel")
      .addLineItem(SessionCreateParams.LineItem.builder().setQuantity(1L)
        .setPriceData(SessionCreateParams.LineItem.PriceData.builder().setCurrency("usd").setUnitAmount(amount)
          .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder().setName("Cinema Booking").build()).build()).build())
      .build();
    Session session = Session.create(params);
    return Map.of("id", session.getId(), "url", session.getUrl());
  }

  @PostMapping("/webhook/stripe")
  public Map<String, String> webhook(){ return Map.of("status", "received"); }
}
