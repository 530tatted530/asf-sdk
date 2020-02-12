package com.appcoins.sdk.billing.payasguest;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.appcoins.sdk.billing.BuyItemProperties;
import com.appcoins.sdk.billing.SharedPreferencesRepository;
import com.appcoins.sdk.billing.WalletInteract;
import com.appcoins.sdk.billing.helpers.AppcoinsBillingStubHelper;
import com.appcoins.sdk.billing.layouts.PaymentMethodsFragmentLayoutBuilder;

public class PaymentMethodsFragment extends Fragment implements PaymentMethodsView {

  private WalletInteract walletInteract;
  private IabView iabView;
  private BuyItemProperties buyItemProperties;
  private PaymentMethodsPresenter paymentMethodsPresenter;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (!(context instanceof IabView)) {
      throw new IllegalStateException("PaymentMethodsFragment must be attached to IabActivity");
    }
    iabView = (IabView) context;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    walletInteract = new WalletInteract(new SharedPreferencesRepository(getActivity()));
    buyItemProperties = (BuyItemProperties) getArguments().getSerializable(
        AppcoinsBillingStubHelper.BUY_ITEM_PROPERTIES);
    paymentMethodsPresenter = new PaymentMethodsPresenter(this, walletInteract);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    PaymentMethodsFragmentLayoutBuilder builder =
        new PaymentMethodsFragmentLayoutBuilder(getActivity(),
            getResources().getConfiguration().orientation, buyItemProperties);

    return builder.build();
  }

  @SuppressLint("ResourceType") @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    paymentMethodsPresenter.requestWallet();
    paymentMethodsPresenter.provideSkuDetailsInformation(buyItemProperties);
  }

  @Override public void setSkuInformation(String fiatPrice, String currencyCode, String appcPrice,
      String sku) {
    Log.d("TAG123", "VIEW: " + fiatPrice + " " + currencyCode + " : " + appcPrice + " : " + sku);
  }

  @Override public void showError() {
    Log.d("TAG123", "ERROR");
  }
}
