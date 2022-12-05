package com.example.natour2122fe.chat;

import static java.util.Collections.singletonList;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.natour2122fe.HomeActivity;
import com.example.natour2122fe.Model.Pathway;
import com.example.natour2122fe.R;
import com.example.natour2122fe.SignalingActivity;
import com.example.natour2122fe.databinding.ActivityListChatBinding;
import com.example.natour2122fe.login.AmplifyCognito;
import com.example.natour2122fe.login.google.GoogleLoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import java.util.List;
import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.api.models.FilterObject;
import io.getstream.chat.android.client.logger.ChatLogLevel;
import io.getstream.chat.android.client.models.Filters;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType;
import io.getstream.chat.android.offline.plugin.configuration.Config;
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModelBinding;
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory;

public class ListChatActivity extends AppCompatActivity {

    ImageView btnSignal, btnUser, btnHome;

    String username,chat;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    AmplifyCognito amplifyCognito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chat);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        toolbar(username);

        // Step 0 - inflate binding
        ActivityListChatBinding binding = ActivityListChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Step 1 - Set up the OfflinePlugin for offline storage
        StreamOfflinePluginFactory streamOfflinePluginFactory = new StreamOfflinePluginFactory(
                new Config(
                        true,
                        true,
                        true,
                        UploadAttachmentsNetworkType.NOT_ROAMING
                ),
                getApplicationContext()
        );
        // Step 2 - Set up the client for API calls with the plugin for offline storage
        ChatClient client = new ChatClient.Builder("r3vuu97ewxcc", getApplicationContext())
                .withPlugin(streamOfflinePluginFactory)
                .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
                .build();
        // Step 3 - Authenticate and connect the user
        User user = new User();
        user.setId("luca1");
        user.setName("luca");
        user.setImage("https://bit.ly/2TIt8NR");
        client.connectUser(
                user,
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoibHVjYTEifQ.KhXrtt59TxMGZAPBaBMVCyrztzM-yYJRyastiLLDf9c"
        ).enqueue();

        // Step 4 - Set the channel list filter and order
        // This can be read as requiring only channels whose "type" is "messaging" AND
        // whose "members" include our "user.id"
        FilterObject filter = Filters.and(
                Filters.eq("type", "messaging"),
                Filters.in("members", singletonList(user.getId()))
        );

        ViewModelProvider.Factory factory = new ChannelListViewModelFactory.Builder()
                .filter(filter)
                .sort(ChannelListViewModel.DEFAULT_SORT)
                .build();

        ChannelListViewModel channelsViewModel =
                new ViewModelProvider(this, factory).get(ChannelListViewModel.class);

        // Step 5 - Connect the ChannelListViewModel to the ChannelListView, loose
        //          coupling makes it easy to customize
        ChannelListViewModelBinding.bind(channelsViewModel, binding.channelListView, this);
        binding.channelListView.setChannelItemClickListener(
                channel -> startActivity(ChatActivity.newIntent(this, channel))
        );


    }
    public boolean googleAccountIsIn (){
        boolean isIn = false;
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            isIn=true;
        }
        return isIn;
    }
    public void toolbar(String username) {
        btnSignal=findViewById(R.id.btnSignal);
        btnSignal.setOnClickListener(v->{
            Intent intent3 = new Intent(ListChatActivity.this, SignalingActivity.class);
            intent3.putExtra("username",username);
            //Log.i("toolbar: ", username);
            startActivity(intent3);
        });


        btnUser = findViewById(R.id.btnUser);
        btnUser.setOnClickListener(v ->
        {
            if(googleAccountIsIn ()){
                //google
                Toast.makeText(this, "Google Account: "+username+ "", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(ListChatActivity.this, GoogleLoginActivity.class);
                startActivity(intent2);

            }else{
                //amazon
                amplifyCognito = new AmplifyCognito(getApplicationContext());
                amplifyCognito.userAttributes(username);}
        });

        btnHome=findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v->{
            Intent intent1 = new Intent(ListChatActivity.this, HomeActivity.class);
            //Log.i("toolbar: ", username);
            startActivity(intent1);
        });
    }
}