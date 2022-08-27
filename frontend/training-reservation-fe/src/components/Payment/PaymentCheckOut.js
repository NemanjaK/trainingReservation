import React, { useEffect, useState } from "react";
import {
    PaymentElement,
    useStripe,
    useElements,
} from "@stripe/react-stripe-js";


export default function PaymentCheckOut(props) {
    const stripe = useStripe();
    const elements = useElements();

    const [email, setEmail] = useState();
    const [message, setMessage] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [clientSecret, setClientSecret] = useState();

    useEffect(() => {
        if (!stripe) {
            return;
        }

        setClientSecret(props.clientSecret);

        if (!clientSecret) {
            return;
        }

        stripe.retrievePaymentIntent(clientSecret).then(({ paymentIntent }) => {
            switch (paymentIntent.status) {
                case "succeeded":
                    setMessage("Payment succeeded!");
                    break;
                case "processing":
                    setMessage("Your payment is processing.");
                    break;
                case "requires_payment_method":
                    setMessage("Your payment was not successful, please try again.");
                    break;
                default:
                    setMessage("Something went wrong.");
                    break;
            }
        });
    }, [stripe]);



    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!stripe || !elements) {
            // Stripe.js has not yet loaded.
            // Make sure to disable form submission until Stripe.js has loaded.
            return;
        }

        setIsLoading(true);

        const { error } = await stripe.confirmPayment({
            redirect: "if_required",
            elements,
            confirmParams: {
                //return_url: "http://localhost:3000/uspesno-placanje",
                receipt_email: email,
            },
        });

        if (!error) {


            alert("Uspesno placanje!")
        } else {
            if (error.type === "card_error" || error.type === "validation_error") {
                setMessage(error.message);
            } else {
                setMessage("An unexpected error occurred.");
            }

            setIsLoading(false);
        }
    };

    return (
        <form id="payment-form" onSubmit={handleSubmit}>
            <label>Email</label>
            <br />
            <input
                id="email-input"
                className="form-control"
                type="text"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="example@gmail.com"
            />
            <br />
            <br />
            <PaymentElement id="payment-element" />
            <button disabled={isLoading || !stripe || !elements} id="submit">
                <span id="button-text">
                    {isLoading ? (
                        <div className="spinner" id="spinner"></div>
                    ) : (
                        "Pay now " + props.amount
                    )}
                </span>
            </button>
            {/* Show any error or success messages */}
            {message && <div id="payment-message">{message}</div>}
        </form>
    );
}
