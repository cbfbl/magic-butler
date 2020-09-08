package com.example.magiccc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void generateTeams(View view){
        TextView teams_text = findViewById(R.id.teams_text_id);
        List<String> players_list = getPlayers();
        int number_of_players = players_list.size();
        if (number_of_players<4){
            teams_text.setText(getString(R.string.not_enough_players));
            return;
        }
        Random rand = new Random();
        StringBuilder output_string = new StringBuilder();
        for (int i=0;i<4;i++){
            String current_player = players_list.get(rand.nextInt(players_list.size()));
            output_string.append(current_player);
            if (i%2==0){
                output_string.append(" with ");
            }
            else if (i!=3){
                output_string.append("\n");
            }
            players_list.remove(current_player);
        }
        teams_text.setText(output_string);
    }

    public void moveToHp(View view){
        setContentView(R.layout.activity_hp_track);
    }

    public ArrayList<String> getPlayers(){
        ArrayList<String> output_array= new ArrayList<String>();
        CheckBox player_1_box = findViewById(R.id.player1_checkbox);
        CheckBox player_2_box = findViewById(R.id.player2_checkbox);
        CheckBox player_3_box = findViewById(R.id.player3_checkbox);
        CheckBox player_4_box = findViewById(R.id.player4_checkbox);
        CheckBox player_5_box = findViewById(R.id.player5_checkbox);
        CheckBox player_6_box = findViewById(R.id.player6_checkbox);
        CheckBox player_7_box = findViewById(R.id.player7_checkbox);
        if (player_1_box.isChecked()){
            output_array.add(getString(R.string.player1));
        }
        if (player_2_box.isChecked()){
            output_array.add(getString(R.string.player2));
        }
        if (player_3_box.isChecked()){
            output_array.add(getString(R.string.player3));
        }
        if (player_4_box.isChecked()){
            output_array.add(getString(R.string.player4));
        }
        if (player_5_box.isChecked()){
            output_array.add(getString(R.string.player5));
        }
        if (player_6_box.isChecked()){
            output_array.add(getString(R.string.player6));
        }
        if (player_7_box.isChecked()){
            output_array.add(getString(R.string.player7));
        }
        return output_array;
    }

    private CountDownTimer tempHpTeam1Timer = new CountDownTimer(3000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            TextView temp_hp1 = findViewById(R.id.temp_hp1);
            temp_hp1.setVisibility(View.INVISIBLE);
            temp_hp1.setText("0");
        }
    };
    private CountDownTimer tempHpTeam2Timer = new CountDownTimer(3000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            TextView temp_hp2 = findViewById(R.id.temp_hp2);
            temp_hp2.setVisibility(View.INVISIBLE);
            temp_hp2.setText("0");
        }
    };

    public void changeHp(View view){
        Button btn = (Button)view;
        char team = btn.getTag().toString().charAt(btn.getTag().toString().length()-1);
        String addition_value = btn.getText().toString();
        int to_add_value;
        if (addition_value.equals("-") || addition_value.equals("+")) {
            to_add_value = 1;
        }
        else {
            to_add_value = Integer.parseInt(addition_value.substring(1));
        }
        if (addition_value.charAt(0)=='-'){
            to_add_value *= -1;
        }
        if (team=='1'){
            TextView hp_team1 = findViewById(R.id.hp_team1);
            TextView temp_hp1 = findViewById(R.id.temp_hp1);
            tempHpTeam1Timer.cancel();
            temp_hp1.setText(addNumberToHp(temp_hp1,to_add_value));
            temp_hp1.setVisibility(View.VISIBLE);
            tempHpTeam1Timer.start();
            hp_team1.setText(addNumberToHp(hp_team1,to_add_value));
        }
        else if (team=='2'){
            TextView hp_team2 = findViewById(R.id.hp_team2);
            TextView temp_hp2 = findViewById(R.id.temp_hp2);
            tempHpTeam2Timer.cancel();
            temp_hp2.setText(addNumberToHp(temp_hp2,to_add_value));
            temp_hp2.setVisibility(View.VISIBLE);
            tempHpTeam2Timer.start();
            hp_team2.setText(addNumberToHp(hp_team2,to_add_value));
        }
    }

    public void moveToTeams(View view){
        tempHpTeam1Timer.cancel();
        tempHpTeam2Timer.cancel();
        setContentView(R.layout.activity_main);
    }

    public String addNumberToHp(TextView hp,int number){
        String hp_str = hp.getText().toString();
        int hp_int  = Integer.parseInt(hp_str)+number;
        return String.valueOf(hp_int);
    }
}
