package com.example.demo.service.interfaces;

import com.stripe.exception.StripeException;

public interface PaymentService {

    String createPaymentIntent(double amount, String username, String email) throws StripeException;
}
