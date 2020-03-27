package com.zjxdd.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.rabtman.wsmanager.WsManager;
import com.rabtman.wsmanager.listener.WsStatusListener;
import com.zjxdd.mvp.R;
import com.zjxdd.mvp.base.BaseMvpActivity;
import com.zjxdd.mvp.contract.ILoginContract;
import com.zjxdd.mvp.presenter.LoginPresenter;
import com.zjxdd.mvp.wslivedemo.LiveActivity;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okio.ByteString;

/**
 * $
 *
 * @author admin
 * @date 2020-03-26 13:50
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter, LoginActivity> implements ILoginContract.IloginView {
    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    private JWebSocketClient webSocketClient;

    @Override
    protected LoginPresenter createPresenter() {
        return LoginPresenter.getInstance();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

        URI uri = URI.create("ws://10.0.129.133:9000");
        webSocketClient = new JWebSocketClient(uri) {
            @Override
            public void onMessage(String message) {
                //message就是接收到的消息
                Log.e("JWebSClientService", message);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        etPassword.setText(message);
                    }
                });

            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    webSocketClient.connectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(this, LiveActivity.class));
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onError(String message) {

    }


    @OnClick(R.id.bt_login)
    public void onViewClicked() {
        getPresenter().userLogin();
//        wsManager.sendMessage(etAccount.getText().toString());
        if (webSocketClient != null && webSocketClient.isOpen()) {
            webSocketClient.send(etAccount.getText().toString());
        }
    }


    public class JWebSocketClient extends WebSocketClient {
        public JWebSocketClient(URI serverUri) {
            super(serverUri, new Draft_6455());
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            Log.e("JWebSocketClient", "onOpen()");
        }

        @Override
        public void onMessage(String message) {
            Log.e("JWebSocketClient", "onMessage()");
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            Log.e("JWebSocketClient", "onClose()"+code+reason+remote);
        }

        @Override
        public void onError(Exception ex) {
            Log.e("JWebSocketClient", "onError()"+ex.getMessage());
        }
    }
}
