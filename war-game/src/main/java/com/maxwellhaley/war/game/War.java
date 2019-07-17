package com.maxwellhaley.war.game;

import java.io.IOException;
import java.util.Random;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.maxwellhaley.war.game.window.MainMenu;

public class War {

  public static void main(String[] args) {
    Terminal terminal = null;
    try {
      terminal = new DefaultTerminalFactory().createTerminal();
      Screen screen = new TerminalScreen(terminal);
      screen.startScreen();

      MultiWindowTextGUI gui = new MultiWindowTextGUI(screen,
              new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
      gui.addWindowAndWait(new MainMenu());
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (terminal != null) {
        try {
          terminal.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
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
