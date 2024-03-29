package com.example.demo.service.impl;

import com.example.demo.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerSearchResult;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerSearchParams;
import com.stripe.param.PaymentIntentCreateParams;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    static class CreatePaymentResponse {
        private String clientSecret;
        private String paymentIntentId;
        public CreatePaymentResponse(String clientSecret, String paymentIntentId) {
            this.clientSecret = clientSecret;
            this.paymentIntentId = paymentIntentId;
        }
    }

    private static Gson gson = new Gson();

    @Override
    public String createPaymentIntent(double amount, String username, String email) throws StripeException {
        Stripe.apiKey = secretKey;
        long amountInt = (new Double(amount)).longValue();

        String customerId = hasCustomer(email);
        if(customerId == null) {
            customerId = createCustomer(email, username);
        }
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amountInt*100)
                        .setCurrency("EUR")
                        .setReceiptEmail(email)
                        .setCustomer(customerId)
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret(), paymentIntent.getId());

        return gson.toJson(paymentResponse);
    }

    private String createCustomer(String email, String username) throws StripeException {
        CustomerCreateParams customerParams =
                CustomerCreateParams
                        .builder()
                        .setName(username)
                        .setEmail(email)
                        .setPaymentMethod("pm_card_visa")
                        .setInvoiceSettings(
                                CustomerCreateParams.InvoiceSettings
                                        .builder()
                                        .setDefaultPaymentMethod("pm_card_visa")
                                        .build()
                        )
                        .build();

        Customer customer = Customer.create(customerParams);
        return customer.getId();

    }

    private String hasCustomer(String email) throws StripeException {
        CustomerSearchParams params =
                CustomerSearchParams
                        .builder()
                        .setQuery("email:'"+email+"'")
                        .build();

        CustomerSearchResult result = Customer.search(params);
        if(!result.getData().isEmpty()) {
            return result.getData().get(0).getId();
        }
        return null;
    }
}
