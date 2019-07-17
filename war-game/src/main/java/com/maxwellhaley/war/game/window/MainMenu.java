package com.maxwellhaley.war.game.window;

import java.util.Arrays;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.LinearLayout.Alignment;
import com.googlecode.lanterna.gui2.Panel;

public class MainMenu extends BasicWindow {

  public MainMenu() {
    super("");

    setHints(Arrays.asList(Hint.CENTERED));

    Button pvc = new Button("Player vs. CPU");
    pvc.setLayoutData(LinearLayout.createLayoutData(Alignment.Center));

    Panel p = new Panel(new LinearLayout(Direction.VERTICAL))
            .addComponent(new Label(" ▄█     █▄     ▄████████    ▄████████ \n" +
                    "███     ███   ███    ███   ███    ███ \n" +
                    "███     ███   ███    ███   ███    ███ \n" +
                    "███     ███   ███    ███  ▄███▄▄▄▄██▀ \n" +
                    "███     ███ ▀███████████ ▀▀███▀▀▀▀▀   \n" +
                    "███     ███   ███    ███ ▀███████████ \n" +
                    "███ ▄█▄ ███   ███    ███   ███    ███ \n" +
                    " ▀███▀███▀    ███    █▀    ███    ███ \n" +
                    "                           ███    ███ \n" +
                    "\n" +
                    ""));

    p.addComponent(new EmptySpace());

    p.addComponent(new Button("Player vs. CPU", new Runnable() {

      @Override
      public void run() {
        close();
      }
    })
            .setLayoutData(LinearLayout.createLayoutData(Alignment.Center)));

    p.addComponent(new Button("Plaver vs. Player", new Runnable() {

      @Override
      public void run() {
        close();
      }
    })
            .setLayoutData(LinearLayout.createLayoutData(Alignment.Center)));
    setComponent(p);
  }

}
