import React, { useState, useEffect } from "react";
import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";
import PaymentCheckOut from "./PaymentCheckOut";
import { urlConfig } from '../../urlConfig';
import './PaymentForm.css'

const stripePromise = loadStripe(
    "pk_test_51Lal3dEaiCdtKK0nluyizbPtAbecr3s7GaQGkwwvraswgfnTxrkuKdsqGYX5GV0l3aK7x5FUc6QMlXjZfzm37xco004gomqPg9"
);

export default function StripeForm(props) {

    const [clientSecret, setClientSecret] = useState("");
    const [paymentIntentId, setPaymentIntentId] = useState(); 
  //  const additionalInformations = props.additionalInformations
//    console.log(clientSecret);

    useEffect(() => {
        stripePayment(props.amount)
    }, [props.amount]);

    const stripePayment = async (amount) => {
        try {
            const name = 'name';
            const email = 'email@gmail.com';

            fetch(`${urlConfig.trainingUrl}/api/payment/create`, {
                method: 'POST',
                headers: {
                    'amount': amount,
                    'username': name,
                    'email': email
                },
            })
                .then((response) => {
                    if (!response.ok) {
                        return Promise.reject(response);
                    }
                    console.log(response)
                    return response.json();
                })
                .then((data) => {
                    setClientSecret(data.clientSecret)
                    setPaymentIntentId(data.paymentIntentId)
                    console.log(data);
                })
                .catch((error) => {
                    console.log(error);
                });
           ;
            
        } catch (error) {
            alert("Greska! " + error);
        }
    };

    const appearance = {
        theme: "flat",
    };
    const options = {
        clientSecret: clientSecret,
        appearance
    };

    return (
        <div className="App">
            {clientSecret && (
                <>
                <Elements options={options} stripe={stripePromise}>
                    
                    <PaymentCheckOut clientSecret={clientSecret}  paymentIntentId={paymentIntentId} amount={props.amount} />
                </Elements>
                </>
            )}
        </div>
    );
}
