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
import com.maxwellhaley.war.game.GameStatus;
import com.maxwellhaley.war.game.GameType;

public class InstructionWindow extends BasicWindow {

  public InstructionWindow() {
    super("");

    setHints(Arrays.asList(Hint.CENTERED));

    Button pvc = new Button("Player vs. CPU");
    pvc.setLayoutData(LinearLayout.createLayoutData(Alignment.Center));

    Panel p = new Panel(new LinearLayout(Direction.VERTICAL))
            .addComponent(new Label("How to play the game"));

    p.addComponent(new EmptySpace());

    p.addComponent(new Button("Declare WAR!", new Runnable() {

      @Override
      public void run() {
        close();
      }
    })
            .setLayoutData(LinearLayout.createLayoutData(Alignment.Center)));

    setComponent(p);
  };
}
