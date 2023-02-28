package com.MahadevanRDJ.carparkingmanagement.Payment;

class PaymentController implements PaymentControllerCallBack, PaymentModelControllerCallBack {
    private PaymentViewCallBack paymentView;
    private PaymentModelCallBack paymentModel;

    public PaymentController(PaymentViewCallBack paymentView) {
        this.paymentView = paymentView;
        this.paymentModel = new PaymentModel(this);
    }

    @Override
    public void getAmount() {
        paymentModel.getAmount();

    }

    @Override
    public void pay(int amount) {
        paymentView.pay(amount);
    }

   

}
