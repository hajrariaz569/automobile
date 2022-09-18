package com.example.automobile.mechanic;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.automobile.R;
import com.example.automobile.chatting.ChatActivity;
import com.example.automobile.complain.ComplainActivity;
import com.example.automobile.constant.EndPoint;
import com.example.automobile.model.Customer;
import com.example.automobile.retrofit.RetrofitClient;
import com.example.automobile.service.GetCustomerLocService;
import com.example.automobile.tracking.MapDirectionActivity;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectBookingDetailActivity extends AppCompatActivity {

    CircleImageView booking_det_picture;
    TextView tv_name, tv_contact, tv_booking_num, tv_booking_datetime, tv_booking_fee, tv_booking_description;
    int getCustomerID, getBookingFee, getBID;
    String getCustomerName, getCustomerContact, getCustomerProfile, getBookingNum, getBookingDatetime,
            getBookingDescription, getStatus;
    Button btn_complete, btn_chat, btn_complain, btn_tracking;
    Dialog dialog;

    double latitude, longitude;

    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_booking_detail);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorAccent));
        booking_det_picture = findViewById(R.id.booking_det_picture);
        tv_name = findViewById(R.id.tv_name);
        tv_booking_num = findViewById(R.id.tv_booking_num);
        tv_booking_datetime = findViewById(R.id.tv_booking_datetime);
        btn_tracking = findViewById(R.id.btn_tracking);
        tv_contact = findViewById(R.id.tv_contact);
        tv_booking_fee = findViewById(R.id.tv_booking_fee);
        tv_booking_description = findViewById(R.id.tv_booking_description);
        btn_complete = findViewById(R.id.btn_complete);
        btn_chat = findViewById(R.id.btn_chat);
        btn_complain = findViewById(R.id.btn_complain);
        getCustomerID = getIntent().getIntExtra("CUSTOMER_ID", 0);
        getBID = getIntent().getIntExtra("BOOKING_ID", 0);
        getBookingFee = getIntent().getIntExtra("BOOKING_FEE", 0);
        getCustomerName = getIntent().getStringExtra("CUSTOMER_NAME");
        getCustomerContact = getIntent().getStringExtra("CUSTOMER_CONTACT");
        getCustomerProfile = getIntent().getStringExtra("CUSTOMER_PROFILE");
        getBookingNum = getIntent().getStringExtra("BOOKING_NUM");
        getBookingDatetime = getIntent().getStringExtra("BOOKING_DATETIME");
        getBookingDescription = getIntent().getStringExtra("BOOKING_DESCRIPTION");
        getStatus = getIntent().getStringExtra("BOOKING_STATUS");

        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(SelectBookingDetailActivity.this, ChatActivity.class);
                chatIntent.putExtra("CUSTOMER_ID", getCustomerID);
                chatIntent.putExtra("CUSTOMER_NAME", getCustomerName);
                chatIntent.putExtra("CUSTOMER_PROFILE", getCustomerProfile);
                chatIntent.putExtra("CHATCHECK", 2);
                startActivity(chatIntent);
            }
        });
        GetCusLocation(getCustomerID);

        btn_complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),
                        ComplainActivity.class)
                        .putExtra("CUSTOMER_ID", getCustomerID)
                        .putExtra("CUSTOMER_NAME", getCustomerName)
                );
            }
        });

        if (getStatus.equals("A")) {
            btn_chat.setVisibility(View.VISIBLE);
            btn_complete.setVisibility(View.VISIBLE);
            btn_complain.setVisibility(View.VISIBLE);
            btn_tracking.setVisibility(View.VISIBLE);
        } else {
            btn_chat.setVisibility(View.GONE);
            btn_complete.setVisibility(View.GONE);
            btn_complain.setVisibility(View.GONE);
            btn_tracking.setVisibility(View.GONE);
        }
        tv_name.setText(getCustomerName);
        tv_booking_num.setText(getBookingNum);
        tv_booking_datetime.setText(getBookingDatetime);
        tv_contact.setText(getCustomerContact);
        tv_booking_description.setText(getBookingDescription);
        tv_booking_fee.setText(String.valueOf(getBookingFee));
        Glide.with(this)
                .load(EndPoint.IMAGE_URL + getCustomerProfile)
                .placeholder(R.drawable.account)
                .into(booking_det_picture);

        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPaymentDialog();
            }
        });

        btn_tracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seeDirection = new Intent(SelectBookingDetailActivity.this,
                        MapDirectionActivity.class);
                seeDirection.putExtra("CUSTOMER_LATITUDE", latitude);
                seeDirection.putExtra("CUSTOMER_LONGITUDE", longitude);
                seeDirection.putExtra("CUSTOMER_NAME", getCustomerName);
                seeDirection.putExtra("CHECK", 2);
                startActivity(seeDirection);
            }
        });

    }

    private void ShowPaymentDialog() {
        dialog = new Dialog(SelectBookingDetailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_payment);
        EditText edt_additional_payment = dialog.findViewById(R.id.edt_additional_payment);
        EditText edt_additional_sname = dialog.findViewById(R.id.edt_additional_sname);
        Button btn_add_en = dialog.findViewById(R.id.btn_add_en);

        btn_add_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_additional_payment.getText().toString().isEmpty()) {
                    startActivity(new Intent(getApplicationContext(),
                            BillActivity.class)
                            .putExtra("BOOKING_ID", getBID)
                            .putExtra("ADDITIONAL_PRICE", 0)
                            .putExtra("BOOKING_NUM", getBookingNum)
                            .putExtra("CUSTOMER_NAME", getCustomerName)
                            .putExtra("BOOKING_FEE", getBookingFee)
                            .putExtra("ADDITIONAL_SERVICE", edt_additional_sname.getText().toString()));
                } else {
                    startActivity(new Intent(getApplicationContext(),
                            BillActivity.class)
                            .putExtra("BOOKING_ID", getBID)
                            .putExtra("BOOKING_NUM", getBookingNum)
                            .putExtra("CUSTOMER_NAME", getCustomerName)
                            .putExtra("BOOKING_FEE", getBookingFee)
                            .putExtra("ADDITIONAL_PRICE", Integer.parseInt(edt_additional_payment.getText().toString()))
                            .putExtra("ADDITIONAL_SERVICE", edt_additional_sname.getText().toString()));
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void GetCusLocation(int ID) {
        customer = new Customer();
        GetCustomerLocService service = RetrofitClient.getClient().create(GetCustomerLocService.class);
        Call<Customer> call = service.getLocation(ID);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful()) {
                    customer = response.body();
                    if (!customer.isError()) {
                        Toast.makeText(getApplicationContext(),
                                customer.getMessage(), Toast.LENGTH_SHORT).show();
                        latitude = customer.getCustomer_lat();
                        longitude = customer.getCustomer_lng();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                customer.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}