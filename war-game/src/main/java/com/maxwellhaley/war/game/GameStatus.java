package com.maxwellhaley.war.game;

public final class GameStatus {
  
  private GameType gameType;
  private static GameStatus INSTANCE;
  
  private GameStatus() {
  }
  
  public static GameStatus getInstance() {
    if(INSTANCE == null) {
      INSTANCE = new GameStatus();
    }
    
    return INSTANCE;
  }
  
  public void setGameType(GameType gameType) {
    this.gameType = gameType;
  }
  
  public GameType getGameType() {
    return gameType;
  }

}
