import java.util.ArrayList;
import processing.data.JSONArray;
/**
*/
public interface ClientContext{
  
  void networkError(Throwable t);
  boolean isHost();
  Player[] getPlayers();
  Level getLevel();
  byte[] loadBytes(String file);
  String getName();
  void setName(String newName);
  ArrayList<Client> getClients();
  String getGameVersion();
  boolean inGame();
  void setIngame(boolean newInGame);
  boolean prevousInGame();
  String getMenu();
  int getSessionTime();
  boolean inMenu();
  LeaderBoard getLeaderBoard();
  boolean isLevelComplete();
  boolean devModeActive();
  void setPlayerNames(ArrayList<String> names);
  void setCurrentPlayer(int playerIndex);
  void setSessionTime(int newTime);
  void setSelectedLevelInfo(SelectedLevelInfo info);
  void loadLevel(String path);
  void setBestTime(int time);
  int getBestTime();
  void loadUGCList();
  ArrayList<String> getUGCLevelNames();
  JSONArray loadJSONArray(String file);
  String getAppdata();
  String getLevelHash(String file);
  void setInMenu(boolean state);
  void setMenu(String newMenu);
  void setTimerEndTime(int time);
  void setStartTime(int time);
  void setPrevousInGame(boolean preIg);
  void setLeaderBoard(LeaderBoard newLeaderBoard);
  void setRootPath(String path);
  void setLevelComplete(boolean complete);
  void setLevel(Level l);
  boolean getEndReached();
  float random(int start,int end);
  String getRootPath();
  SelectedLevelInfo getMultyplayerSelectedLevel();
  int millis();
  void saveBytes(String file, byte[] data);
}
