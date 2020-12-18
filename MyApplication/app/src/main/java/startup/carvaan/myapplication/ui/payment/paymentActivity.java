package startup.carvaan.myapplication.ui.payment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gocashfree.cashfreesdk.CFPaymentService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import startup.carvaan.myapplication.ProgDialogue;
import startup.carvaan.myapplication.R;
import startup.carvaan.myapplication.ui.coins.coinModal;
import startup.carvaan.myapplication.ui.mainActivity.MainActivity;
import startup.carvaan.myapplication.ui.user.User;

public class paymentActivity extends AppCompatActivity {

    private static final String TAG = "Anubhav";
    private EditText orderAmount;
    private TextView coins;
    private Button payAmount;
    User user=new User();
    coinModal coinModal=new coinModal();
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    ICloudFunction iCloudFunction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().setTitle("Add Cash ");
        getSupportActionBar().setElevation(0);
        coins=findViewById(R.id.coins);
        iCloudFunction=RetrofitClient.getInstance().create(ICloudFunction.class);
        orderAmount=findViewById(R.id.amount);
        orderAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0) {
                    coins.setText("0 coins");
                }
                else{
                    coins.setText(String.valueOf(Integer.valueOf((int) (Integer.valueOf(orderAmount.getText().toString())/Double.valueOf(coinModal.getValue()))))+" coins");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        payAmount=findViewById(R.id.pay);
        payAmount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ProgressDialog progressDialog = new ProgressDialog(paymentActivity.this);
                ProgDialogue progDialogue = new ProgDialogue("message","title",false,paymentActivity.this,progressDialog);
                progDialogue.showDialogue(progressDialog);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        progressDialog.dismiss();
                        makePaymentRequest();
                    }
                },2000);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            user.addCash(Double.valueOf(orderAmount.getText().toString()));
            Toast.makeText(paymentActivity.this,"done",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(paymentActivity.this,"failed",Toast.LENGTH_LONG).show();
        }
    }

    private void makePaymentRequest() {
        String orderId= UUID.randomUUID().toString();
        Map<String ,String > payment=new HashMap<>();
        payment.put("appId","3008091222ee68f67fbc5fd1c08003");
        payment.put("orderId",orderId);
        payment.put("orderAmount",orderAmount.getText().toString());
        payment.put("orderCurrency","INR");
        payment.put("customerPhone","+918171365728");
        payment.put("customerEmail","test@gmail.com");
        compositeDisposable.add(iCloudFunction.getToken(orderId,orderAmount.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                        subscribe(new Consumer<CashFreeToken>() {
                                      @Override
                                      public void accept(CashFreeToken cashFreeToken) throws Exception {
                                          if(cashFreeToken.getStatus().equals("OK")){
                                              CFPaymentService.getCFPaymentServiceInstance().doPayment(paymentActivity.this,payment,cashFreeToken.getCftoken(),"TEST");
                                          }
                                      }
                                  }, new Consumer<Throwable>() {
                                      @Override
                                      public void accept(Throwable throwable) throws Exception {
                                          Toast.makeText(paymentActivity.this,""+throwable.getMessage(),Toast.LENGTH_LONG).show();
                                      }
                                  }
                        ));
    }
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}