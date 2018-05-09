package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import be.lsinf1225gr12.minipoll.minipoll.activity.adapter.ChooseFriendAdapter;
import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class ChooseFriendActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_friend);
        ListView mListView = findViewById(R.id.listView);

        String[] friendList= {"Mario","Luigi","Peach","Daisy","Bowser","Wario","Waluigi","Koopa","Goomba","Yoshi","Donkey Kong"}; // example, to be removed
        // Set a String[] with every friend's name

        ChooseFriendAdapter adapter = new ChooseFriendAdapter(this, friendList);
        mListView.setAdapter(adapter);
    }
}
