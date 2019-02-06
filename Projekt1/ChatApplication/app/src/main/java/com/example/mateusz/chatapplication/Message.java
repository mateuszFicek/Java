package com.example.mateusz.chatapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.mateusz.chatapplication.Adapter.MessageAdapter;
import com.example.mateusz.chatapplication.Fragments.APIService;
import com.example.mateusz.chatapplication.Model.ChatWindow;
import com.example.mateusz.chatapplication.Model.User;
import com.example.mateusz.chatapplication.Notification.Client;
import com.example.mateusz.chatapplication.Notification.Data;
import com.example.mateusz.chatapplication.Notification.Response;
import com.example.mateusz.chatapplication.Notification.Sender;
import com.example.mateusz.chatapplication.Notification.Token;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Mateusz on 30.12.2018.
 */

/**
 * Message class which displays chat.
 */
public class Message extends AppCompatActivity {

    CircleImageView profilePicture;
    TextView username;
    ImageButton sendButton;
    EditText messageToSend;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    String userid;
    MessageAdapter messageAdapter;
    List<ChatWindow> chats;
    RecyclerView recyclerView;
    ValueEventListener seenListener;
    Intent intent;
    byte[] encrypted;
    String encryptedtext;
    String decrypted;

    APIService apiService;
    boolean notify = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Message.this, Chat.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        profilePicture = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        intent = getIntent();

        userid = intent.getStringExtra("userid");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
        messageToSend = findViewById(R.id.text_send);
        sendButton = findViewById(R.id.btn_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notify = true;
                String mesg = messageToSend.getText().toString();
                if (!mesg.equals("")) {
                    sendMessage(firebaseUser.getUid(), userid, mesg);
                } else {
                    Toast.makeText(Message.this, "Please type your message!", Toast.LENGTH_LONG).show();
                }
                messageToSend.setText("");
            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                username.setText(user.getUsername());
                if (user.getImageURL().equals("default")) {
                    profilePicture.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(getApplicationContext()).load(user.getImageURL()).into(profilePicture);
                }
                readMessages(firebaseUser.getUid(), userid, user.getImageURL());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        seenMessage(userid);
    }

    /**
     * It updates Database when message was seen by receiving person.
     *
     * @param userid - id of person to whom this message will be send
     */
    private void seenMessage(final String userid) {
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        seenListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatWindow chat = snapshot.getValue(ChatWindow.class);
                    if (chat.getRecievingUser().equals(firebaseUser.getUid()) && chat.getSendingUser().equals(userid)) {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("isseen", true);
                        snapshot.getRef().updateChildren(hashMap);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * It sends the message to given person.
     * Sending means putting message into database.
     * Also sends notification when sending is successful.
     *
     * @param sendingUser   - id of person who sends the message
     * @param recievingUser - id of person who receives message
     * @param message       - message body
     */
    public void sendMessage(String sendingUser, final String recievingUser, String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sendingUser", sendingUser);
        hashMap.put("recievingUser", recievingUser);
        hashMap.put("message", Encrypt(message));
        hashMap.put("isseen", false);
        reference.child("Chats").push().setValue(hashMap);

        final String msg = message;

        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if (notify) {
                    sendNotification(recievingUser, user.getUsername(), msg);
                }
                notify = false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * Sends notification to person who will receive the message.
     *
     * @param reciever - id of receiving person
     * @param username - username of sending person
     * @param message  - message that will be displayed in notification
     */
    private void sendNotification(String reciever, final String username, final String message) {
        DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens");
        Query query = tokens.orderByKey().equalTo(reciever);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Token token = snapshot.getValue(Token.class);
                    Data data = new Data(firebaseUser.getUid(), R.mipmap.ic_launcher, username + ": " + message, "New Message", userid);
                    Sender sender = new Sender(data, token.getToken());
                    apiService.sendNotification(sender).enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            if (response.code() == 200) {
                                if (response.body().success != 1) {
                                    Toast.makeText(Message.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    /**
     * Reads messages from database and puts them into recyclerView.
     *
     * @param userId    - id of sending person
     * @param reciverId - id of receiving person
     * @param imageUrl  - Url to profile picture
     */
    private void readMessages(final String userId, final String reciverId, final String imageUrl) {
        chats = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chats.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatWindow chat = snapshot.getValue(ChatWindow.class);
                    if (chat.getRecievingUser().equals(userId) && chat.getSendingUser().equals(reciverId) || chat.getRecievingUser().equals(reciverId) && chat.getSendingUser().equals(userId)) {
                        chat.setMessage(Decrypt(chat.getMessage()));
                        chats.add(chat);
                    }
                }
                messageAdapter = new MessageAdapter(Message.this, chats, imageUrl);
                recyclerView.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    /**
     * Checks if currently user is using app.
     *
     * @param userid - id of user
     */
    private void currentUser(String userid) {
        SharedPreferences.Editor editor = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
        editor.putString("currentuser", userid);
        editor.apply();
    }

    /**
     * Changes status when person starts/exists app.
     *
     * @param status - status type
     */
    private void status(String status) {
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", status);

        reference.updateChildren(hashMap);
    }

    /**
     * Changes the status when person starts app.
     */
    @Override
    protected void onResume() {
        super.onResume();
        status("online");
        currentUser(userid);
    }

    /**
     * Changes the status when person exists app.
     */
    @Override
    protected void onPause() {
        super.onPause();
        reference.removeEventListener(seenListener);
        status("offline");
        currentUser("none");
    }

    /**
     * Encrypts given String.
     *
     * @param pInput - given string
     * @return - encrypted string
     */
    public String Encrypt(String pInput) {

        try {

            String Input = pInput;
            String key = "Bar12345Bar12345Bar12345Bar12345";

            SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(Input.getBytes());
            encryptedtext = Base64.encodeToString(encrypted, Base64.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedtext;
    }

    /**
     * Decrypts given String.
     *
     * @param pInput - given string
     * @return - decrypted string
     */
    public String Decrypt(String pInput) {

        try {

            String Input = pInput;

            String key = "Bar12345Bar12345Bar12345Bar12345";

            SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] data = Base64.decode(Input, Base64.DEFAULT);
            decrypted = new String(cipher.doFinal(data));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;
    }

}
