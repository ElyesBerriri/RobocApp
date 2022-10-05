package com.example.robocapp;

public class Team {
    public String team_id;
    public String team_name;
    public String team_chef;
    public String concours;
    public boolean pres = false;
    public long num_tel;
    public float score_jury = -1;
    public int score_homologation = -1;
    public float time = 0;
    public boolean eliminated = false;
    public String cause = "";

    public Team() {

    }

    public Team(String team_id, String team_name, String team_chef, String concours, long num_tel, boolean pres, float score_jury, int score_homologation, float t, boolean e, String c) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_chef = team_chef;
        this.concours = concours;
        this.pres = pres;
        this.num_tel = num_tel;
        this.score_jury = score_jury;
        this.score_homologation = score_homologation;
        this.time = t;
        this.eliminated = e;
        this.cause = c;

    }

    public Team(String team_id, String team_name, String team_chef, String concours, long num_tel) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_chef = team_chef;
        this.concours = concours;
        this.num_tel = num_tel;
    }


    //homologation
    public String getTeam_id() {
        return team_id;
    }

    public int getScore_homologation() {
        return score_homologation;
    }

    public String getConcours() {
        return concours;
    }
    //============

    public Boolean getPres() {
        return pres;
    }

    public float getScore_jury() {
        return score_jury;
    }


   // public int compareTo(Team compareteam) {
     //   int comparescore_jury = ((Team) compareteam).getScore_jury();
        /* For Ascending order*/
       // return comparescore_jury - this.score_jury;
    //}
}