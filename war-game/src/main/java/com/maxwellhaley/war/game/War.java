package com.maxwellhaley.war.game;

import java.util.Random;

public class War {

  public static void main(String[] args) {
    clearScreen();
    System.out.println("▄█     █▄     ▄████████    ▄████████ \n" +
            "███     ███   ███    ███   ███    ███ \n" +
            "███     ███   ███    ███   ███    ███ \n" +
            "███     ███   ███    ███  ▄███▄▄▄▄██▀ \n" +
            "███     ███ ▀███████████ ▀▀███▀▀▀▀▀   \n" +
            "███     ███   ███    ███ ▀███████████ \n" +
            "███ ▄█▄ ███   ███    ███   ███    ███ \n" +
            " ▀███▀███▀    ███    █▀    ███    ███ \n" +
            "                           ███    ███ \n" +
            "\n" +
            "");
    System.out.println("Welcome to WAR! Pick a game mode:");
  }

  private static void clearScreen() {
    System.out.print("\033[2J");
  }

  private String victoryVerbs() {
    String[] verbs = { "crushes", "destroys", "annihilates", "slaughters",
            "demolishes", "exterminates", "obliterates", "massacres",
            "wrecks", "rekts", "owns", "pwns", "abrogates", "humilates",
            "kills", "slayes", "scalps", "immolates", "crucifies",
            "sacrifices", "smothers", "ruins", "buries", "lynches",
            "eradicates", "executes", "beheads", "draw and quarters",
            "eviscerates", "butchers" };

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
