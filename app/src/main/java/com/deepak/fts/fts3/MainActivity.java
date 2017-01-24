package com.deepak.fts.fts3;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.deepak.fts.R;
import com.deepak.fts.database.ConnectDB;
import com.deepak.fts.databinding.ActivityMainBinding;
import com.deepak.fts.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ConnectDB connectDB;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        connectDB = ConnectDB.getInstance(this);
        insertFTS3DummyData();

        binding.search.setAdapter(new SearchAdapter(this));
        binding.search.setLoadingIndicator(binding.pbLoadingIndicator);

    }

    /**
     * add the dummy data for FTS 3 Table
     */
    private void insertFTS3DummyData() {
        connectDB.deleteFTS3Data();

        ArrayList<User> mList = generateDummyData();
        connectDB.insertFTS3Data(mList);
    }

    /**
     * function to generate the dummy list of users
     */
    private ArrayList<User> generateDummyData() {
        ArrayList<User> mList = new ArrayList<>();

        // User 1
        User user = new User();
        user.setName("Joyce Hernandez");
        user.setEmail("joyce.hernandez29@example.com");
        user.setDesc("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        mList.add(user);

        // user 2
        user = new User();
        user.setName("Frances Andrews");
        user.setEmail("frances.andrews29@example.com");
        user.setDesc("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
        mList.add(user);

        // user 3
        user = new User();
        user.setName("Hazel Bates");
        user.setEmail("hazel.bates63@example.com");
        user.setDesc("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.");
        mList.add(user);

        // user 4
        user = new User();
        user.setName("Nelson Moore");
        user.setEmail("nelson.moore83@example.com");
        user.setDesc("Logged in users can view full social security numbers and can save their fake names to use later.");
        mList.add(user);

        // user 5
        user = new User();
        user.setName("Judith Romero");
        user.setEmail("judith.romero16@example.com");
        user.setDesc("Want to create your own customized data generator for your application?");
        mList.add(user);

        // user 6
        user = new User();
        user.setName("Louella Knight");
        user.setEmail("louella.knight36@example.com");
        user.setDesc("Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est.");
        mList.add(user);

        // user 7
        user = new User();
        user.setName("Kent Gonzalez");
        user.setEmail("kent.gonzalez44@example.com");
        user.setDesc("Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.");
        mList.add(user);

        // user 8
        user = new User();
        user.setName("Levi Howard");
        user.setEmail("levi.howard81@example.com");
        user.setDesc("Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?");
        mList.add(user);

        // user 9
        user = new User();
        user.setName("Austin Hansen");
        user.setEmail("austin.hansen14@example.com");
        user.setDesc("Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        mList.add(user);

        // user 10
        user = new User();
        user.setName("Hilda Parker");
        user.setEmail("hilda.parker55@example.com");
        user.setDesc("In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided.");
        mList.add(user);

        return mList;
    }
}
