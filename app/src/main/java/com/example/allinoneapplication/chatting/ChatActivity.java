package com.example.allinoneapplication.chatting;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.allinoneapplication.R;
import com.example.allinoneapplication.adapter.ChatAdapter;
import com.example.allinoneapplication.constant.EndPoint;
import com.example.allinoneapplication.constant.TinyDB;
import com.example.allinoneapplication.model.Chat;
import com.example.allinoneapplication.retrofit.RetrofitClient;
import com.example.allinoneapplication.service.GetMessageService;
import com.example.allinoneapplication.service.SendMessageService;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    CircleImageView chat_image;
    TextView Chat_name;
    RecyclerView Chat_recycler_view;
    EditText edt_chat_message;
    ImageButton send_chatmessage_btn;
    String MName, MImage, Cname, Cimg;
    int MID, getuserID, getChatCheck, CID;
    Chat chat;
    List<Chat> chatList = new ArrayList<>();
    int code;
    TinyDB tinyDB;
    ChatAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorAccent));

        init();

    }

    private void init() {
        tinyDB = new TinyDB(this);
        chat_image = findViewById(R.id.chat_image);
        Chat_name = findViewById(R.id.Chat_name);
        Chat_recycler_view = findViewById(R.id.Chat_recycler_view);
        edt_chat_message = findViewById(R.id.edt_chat_message);
        send_chatmessage_btn = findViewById(R.id.send_chatmessage_btn);
        send_chatmessage_btn.setOnClickListener(this);
        GetData();
    }


    private void GetData() {
        getChatCheck = getIntent().getIntExtra("CHATCHECK", 0);
        if (getChatCheck == 1) {
            MName = getIntent().getStringExtra("MECHANIC_NAME");
            MImage = getIntent().getStringExtra("MECHANIC_PROFILE");
            MID = getIntent().getIntExtra("MECHANIC_ID", 0);
            getuserID = tinyDB.getInt("CUSTOMERID");
            Glide.with(ChatActivity.this)
                    .load(EndPoint.IMAGE_URL + MImage)
                    .placeholder(R.drawable.account)
                    .into(chat_image);
            Chat_name.setText(MName);
            GetMessages(getuserID, MID);
        } else if (getChatCheck == 2) {
            Cname = getIntent().getStringExtra("CUSTOMER_NAME");
            Cimg = getIntent().getStringExtra("CUSTOMER_PROFILE");
            CID = getIntent().getIntExtra("CUSTOMER_ID", 0);
            getuserID = tinyDB.getInt("MECHANIC_ID");
            Glide.with(ChatActivity.this)
                    .load(EndPoint.IMAGE_URL + Cimg)
                    .placeholder(R.drawable.account)
                    .into(chat_image);
            Chat_name.setText(Cname);
            GetMessages(CID, getuserID);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_chatmessage_btn:
                if (getChatCheck == 1) {
                    if (edt_chat_message.getText().toString().isEmpty()) {
                        Toast.makeText(this,
                                "Empty message not send", Toast.LENGTH_SHORT).show();
                    } else {
                        SendMessageToMechanic();
                    }
                } else if (getChatCheck == 2) {
                    if (edt_chat_message.getText().toString().isEmpty()) {
                        Toast.makeText(this,
                                "Empty message not send", Toast.LENGTH_SHORT).show();
                    } else {
                        SendMessageToCustomer();
                    }
                }
                break;
        }
    }

    /**
     * function that send message to Mechanic
     */
    private void SendMessageToMechanic() {
        SendMessageService service = RetrofitClient.getClient().create(SendMessageService.class);
        Call<Chat> call = service.sendMessage(edt_chat_message.getText().toString(),
                getuserID, MID, "p");
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                    chat = response.body();
                    if (chat != null) {
                        boolean error = chat.isError();
                        if (!error) {
                            Toast.makeText(ChatActivity.this,
                                    chat.getMessage(), Toast.LENGTH_SHORT).show();
                            edt_chat_message.setText("");
                            GetMessages(getuserID, MID);
                        } else {
                            Toast.makeText(ChatActivity.this,
                                    chat.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ChatActivity.this,
                                response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChatActivity.this,
                            response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                Toast.makeText(ChatActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * function to get Messages
     */
    private void GetMessages(final int u_id, final int d_id) {
        if (chatList != null) {
            chatList.clear();
        }
        GetMessageService service = RetrofitClient.getClient().create(GetMessageService.class);

        Call<JsonObject> call = service.getMessages();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) {
                    code = response.code();
                }

                if (code == 200) {

                    try {

                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());

                        JSONArray jsonArray = jsonObject.getJSONArray("records");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);

                            chatList.add(new Chat(
                                    data.getInt("chat_id"),
                                    data.getString("chat_message"),
                                    data.getInt("sender_id"),
                                    data.getInt("receiver_id"),
                                    data.getString("sender_type"),
                                    data.getString("chat_datetime")));

                            adapter = new ChatAdapter(chatList, u_id, d_id, getChatCheck);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatActivity.this);
                            linearLayoutManager.setStackFromEnd(true);
                            Chat_recycler_view.setLayoutManager(linearLayoutManager);
                            Chat_recycler_view.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (code == 404) {
                    Toast.makeText(ChatActivity.this, "Server connectivity error!", Toast.LENGTH_SHORT).show();
                } else if (code == 500) {
                    Toast.makeText(ChatActivity.this, "Internal Server error!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                // Log error here since request failed
                Toast.makeText(ChatActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * function for Send Message to customer
     */
    private void SendMessageToCustomer() {
        SendMessageService service = RetrofitClient.getClient().create(SendMessageService.class);
        Call<Chat> call = service.sendMessage(edt_chat_message.getText().toString(),
                getuserID, CID, "d");
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                    chat = response.body();
                    if (chat != null) {
                        boolean error = chat.isError();
                        if (!error) {
                            Toast.makeText(ChatActivity.this,
                                    chat.getMessage(), Toast.LENGTH_SHORT).show();
                            edt_chat_message.setText("");
                            GetMessages(CID, getuserID);
                        } else {
                            Toast.makeText(ChatActivity.this,
                                    chat.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ChatActivity.this,
                                response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChatActivity.this,
                            response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                Toast.makeText(ChatActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}