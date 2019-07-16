package com.maxwellhaley.war.core.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.maxwellhaley.war.core.exception.NotEnoughCashException;
import com.maxwellhaley.war.core.model.Player;

public class PlayerTests {

  @Test
  void playerGoesAllIn() throws NotEnoughCashException {
    Player player = new Player("James");
    player.bet(1000);
    assertEquals(0, player.getCash(),
            "Player does not have expected amount of cash.");
  }

  @Test
  void playerBetsRemainingWith990() throws NotEnoughCashException {
    Player player = new Player("James");
    player.bet(10);
    assertEquals(990, player.getCash(),
            "Player does not have expected amount of cash.");
  }

  @Test
  void playerBetsMoreThanTheyCanChew() throws NotEnoughCashException {
    Player player = new Player("James");
    assertThrows(NotEnoughCashException.class, () -> {
      player.bet(1001);
    }, "Exception not thrown when player bets more cash than they currently have.");
  }
}
