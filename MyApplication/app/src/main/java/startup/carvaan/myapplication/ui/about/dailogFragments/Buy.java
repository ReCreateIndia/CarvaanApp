package startup.carvaan.myapplication.ui.about.dailogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import startup.carvaan.myapplication.R;
import startup.carvaan.myapplication.ui.about.shareDetails;
import startup.carvaan.myapplication.ui.myshares.mysharemodel;
import startup.carvaan.myapplication.ui.user.User;
public class Buy extends DialogFragment {
    User user=new User();
    EditText nos;
    Button buy;
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        Bundle bundle= getArguments();
        final String shareId= bundle.getString("shareid");
        final View view = inflater.inflate(R.layout.fragment_buy,null,false);
        FirebaseFirestore ff=FirebaseFirestore.getInstance();
        shareDetails shareDetails=new shareDetails(shareId);
        nos=view.findViewById(R.id.noofshares);
        buy=view.findViewById(R.id.btn_buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Nos=Integer.valueOf(nos.getText().toString());
                int totalPrice=Nos*Integer.valueOf(shareDetails.getBuyingPrice());
                int credits=Integer.valueOf(user.getEarned())+Integer.valueOf(user.getWinnings());
                if(credits>=totalPrice){
                    final String[] priceHolding = {null};
                    final String[] holdings={null};
                    ff.collection("Users")
                            .document(user.getUser().getUid())
                            .collection("myshares").document(shareId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            priceHolding[0] =documentSnapshot.getString("priceHoldings");
                            holdings[0] =documentSnapshot.getString("holdings");
                        }
                    });
                    Toast.makeText(getContext(),"Proceeding your buying.....",Toast.LENGTH_LONG).show();
                    ff.collection("Users")
                            .document(user.getUser().getUid())
                            .collection("myshares")
                            .document(shareId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            mysharemodel mysharemodel=task.getResult().toObject(mysharemodel.class);
                            if(task.getResult().getString("holdings")==null){
                                String lastPriceHolding=String.valueOf(shareDetails.getBuyingPrice());
                                Map<Object,Object>map=new HashMap<>();
                                map.put("priceHoldings",lastPriceHolding);
                                map.put("holdings",String.valueOf(Nos));
                                ff.collection("Users")
                                        .document(user.getUser().getUid())
                                        .collection("myshares")
                                        .document(shareId).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(totalPrice<=Integer.valueOf(user.getEarned()))
                                            user.removeEarned(totalPrice);
                                        else{
                                            user.removeWinnings(totalPrice-Integer.valueOf(user.getEarned()));
                                            user.removeEarned(Integer.valueOf(user.getEarned()));
                                        }
                                        dialog_buy_success dialog_buy_success=new dialog_buy_success();
                                        dialog_buy_success.show(getChildFragmentManager(), "Dialog_Buy");
                                    }
                                });
                            }
                            else{
                                if(Integer.valueOf(priceHolding[0])==Integer.valueOf(shareDetails.getBuyingPrice())|| Integer.valueOf(holdings[0])==0){
                                    String lastPriceHolding=String.valueOf(shareDetails.getBuyingPrice());
                                    ff.collection("Users")
                                            .document(user.getUser().getUid())
                                            .collection("myshares")
                                            .document(shareId)
                                            .update("holdings",String.valueOf(Integer.valueOf(mysharemodel.getHoldings())+Nos),
                                                    "priceHoldings",lastPriceHolding).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(totalPrice<=Integer.valueOf(user.getEarned()))
                                                user.removeEarned(totalPrice);
                                            else{
                                                user.removeWinnings(totalPrice-Integer.valueOf(user.getEarned()));
                                                user.removeEarned(Integer.valueOf(user.getEarned()));
                                            }
                                            dialog_buy_success dialog_buy_success=new dialog_buy_success();
                                            dialog_buy_success.show(getChildFragmentManager(), "Dialog_Buy");

                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(getContext(),"unable to buy shares because you have other holdings at different price first sell your previous holdings",Toast.LENGTH_LONG).show();
                                }


                            }
                        }
                    });

                }else{
                    Toast.makeText(getContext(),"You do not have sufficient amount",Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onStop() {
        super.onStop();
        getDialog().dismiss();
    }
}