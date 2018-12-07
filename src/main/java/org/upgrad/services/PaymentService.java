package org.upgrad.services;

import org.upgrad.models.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getPaymentMethods();
}
