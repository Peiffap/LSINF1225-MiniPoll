package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.adapter.ChooseFriendAdapter;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class ChooseFriendActivity extends Activity implements OnItemClickListener
{

    public static final String FriendMemory = "be.lsinf1225gr12.minipoll.minipoll.activity.FriendMemory";

    private ArrayList<User> user;
    private ChooseFriendAdapter chooseFriendAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_friend);

        // Chargement des éléments à afficher dans la variable de classe songs
        loadUser();

        ListView myListView = findViewById(R.id.show_listView2);

        // Création de l'adapter pour faire la liaison entre les données (songs) et
        // l'affichage de chaque ligne de la liste.
        chooseFriendAdapter = new ChooseFriendAdapter(this, user);
        myListView.setAdapter(chooseFriendAdapter);

        // Indique que le clic d'un élément de la liste doit appeler la méthode onItemClick d
        // cette classe (this).
        myListView.setOnItemClickListener(this);

    }

    private void loadUser() {

            user = User.getFriends(User.getConnectedUser());;


        // S'il n'y a aucun éléments dans la liste, il faut afficher un message. Ce message est différent
        // s'il y avait une requête de recherche (message du type "Aucun résultat trouvé") ou si
        // l'utilisateur vient directement du menu principal et veut tout afficher (message du type
        // "Aucun élément n'est présent dans votre collection).
        if (user.isEmpty()) {
                MiniPollApp.notifyShort(R.string.list_error);

            // Cloture de l'activité d'affichage de la liste (car liste vide). Retour à l'écran
            // précédent.
            finish();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        // La liste des éléments est ici rechargées car en cas de modification d'un élément, l'ordre
        // a peut-être changé.

        loadUser();

        chooseFriendAdapter.setUser(user);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, MainActivity.class);
        // L'id de l'élément de collection est passé en argument afin que la vue de détails puisse
        // récupérer celui-ci.
        intent.putExtra("s_id", user.get(position).getId());
        startActivity(intent);
    }
}
