package com.example.demo.controllers;

import com.example.demo.service.interfaces.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public String createPayment(@RequestHeader(value = "amount") double amount, @RequestHeader(value = "username") String username,
                                @RequestHeader(value = "email") String email) throws StripeException {
        return paymentService.createPaymentIntent(amount, username, email);
    }
}
