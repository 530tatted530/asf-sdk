package com.appcoins.communication;

import android.content.Context;
import android.content.Intent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class MessageReceiverTest {

  public static final String RECEIVER_URI = "appcoins://receiver_uri";
  private MessageReceiver messageReceiver;
  private MessageReceiver.MessageReceivedListener listener;
  private Context context;

  @Before public void setUp() throws Exception {
    context = Mockito.mock(Context.class);
    listener = Mockito.mock(MessageReceiver.MessageReceivedListener.class);
    messageReceiver = new MessageReceiver(context, RECEIVER_URI);
    messageReceiver.setListener(listener);
  }

  @Test public void onReceive() {
    Intent intent = new Intent();
    intent.putExtra("MESSAGE_ID", 1L);
    Person returnValue = new Person("");
    intent.putExtra("RETURN_VALUE", returnValue);
    messageReceiver.onReceive(context, intent);

    Mockito.verify(listener, times(1))
        .onMessageReceived(1L, returnValue);
  }
}