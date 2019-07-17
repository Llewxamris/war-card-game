package com.maxwellhaley.war.game;

import java.util.Random;

public class War {
  
    public static void main(String[] args) {
       System.out.println("Hello, world!");
    }

    private String victoryVerbs() {
        String[] verbs = { "crushes", "destroys", "annihilates", "slaughters",
                "demolishes", "exterminates", "obliterates", "massacres",
                "wrecks", "rekts", "owns", "pwns", "abrogates", "humilates",
                "kills", "slayes", "scalps", "immolates", "crucifies",
                "sacrifices", "smothers", "ruins", "buries", "lynches",
                "eradicates", "executes", "beheads", "draw and quarters",
                "eviscerate", "butchers" };

        Random rng = new Random();

        return " " + verbs[rng.nextInt(verbs.length)] + " ";
    }

    private String playVerbs() {
        String[] plays = { "fires", "launches", "attacks", "shells", "bombs",
                "bombards" };

        Random rng = new Random();
        return " " + plays[rng.nextInt(plays.length)] + " with a(n) ";
    }
}
