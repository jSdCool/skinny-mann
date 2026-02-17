import java.util.ArrayList;
import processing.data.JSONArray;
/**Context and external access for clietns to interact with the game
*/
public interface ClientContext{
  
  /**Main class network error funcion
  @param t the error that was thrown
  */
  void networkError(Throwable t);
  /**If this client is the session host
  @return true if host
  */
  boolean isHost();
  /**Get the current players
  @return the players array
  */
  Player[] getPlayers();
  /**Get the current level
  @return the current level
  */
  Level getLevel();
  /**Load the bytes of a file
  @param file the file to load
  @return the byte content of a file
  */
  byte[] loadBytes(String file);
  /**Get this player's name
  @return the name of this player
  */
  String getName();
  /**Set the name of this player
  @param newName the name to set to
  */
  void setName(String newName);
  /**Get the list of connected clients
  @return the current client list
  */
  ArrayList<Client> getClients();
  /**Get the current version of this game
  @return the game version
  */
  String getGameVersion();
  /**Get if the game is currently in progress
  @return true if the game is happening
  */
  boolean inGame();
  /**Set wether currently in game
  @param newInGame the new state of in game
  */
  void setIngame(boolean newInGame);
  /**Get if prevously in game
  @return true if prevously in game
  */
  boolean prevousInGame();
  /**Get the currenly open menu
  @return the current menu
  */
  String getMenu();
  /**Get the current session time
  @return the current session time
  */
  int getSessionTime();
  /**Get if a menu is currently open
  @return true if a menu is open
  */
  boolean inMenu();
  /**Get the current leader board
  @return the current leader board
  */
  LeaderBoard getLeaderBoard();
  /**Get if the current level has been completed
  @return true if the level is complete
  */
  boolean isLevelComplete();
  /**Get if dev mode is active
  @return dev mode
  */
  boolean devModeActive();
  /**Set the list of player names
  @param names the list of player names
  */
  void setPlayerNames(ArrayList<String> names);
  /**Set the number of the current player
  @param playerIndex The new index of the current player [0-9]
  */
  void setCurrentPlayer(int playerIndex);
  /**Set the current session time
  @param newTime the new time value
  */
  void setSessionTime(int newTime);
  /**Set the current selected level info
  @param info the new selected level info
  */
  void setSelectedLevelInfo(SelectedLevelInfo info);
  /**Load a level
  @param path the path of the level to load
  */
  void loadLevel(String path);
  /**Set the best time of this user
  @param time the new best time
  */
  void setBestTime(int time);
  /**Get the current best time for this user
  @return the best time
  */
  int getBestTime();
  /**Load the list of UGC levels
  */
  void loadUGCList();
  /**Get the names of the UGC levels loaded from loadUGCList
  @return a list of UGC level names
  */
  ArrayList<String> getUGCLevelNames();
  /**Load a json array from a file
  @param file the name of the file to load
  @return the json array
  */
  JSONArray loadJSONArray(String file);
  /**Get the appdata directory
  @return the system specific appdata folder
  */
  String getAppdata();
  /**Get the hash of a level
  @param file the file path to the level to hash
  @return the hash of all the level files
  */
  String getLevelHash(String file);
  /**Set wther a menu is currently open
  @param state wether in a menu
  */
  void setInMenu(boolean state);
  /**Set the current menu
  @param newMenu the manu to show
  */
  void setMenu(String newMenu);
  /**Set the timer end time
  @param time the time the timer will end
  */
  void setTimerEndTime(int time);
  /**Set the timer start time
  @param time the time the timer will start
  */
  void setStartTime(int time);
  /**Set if this client was prevously in game
  @param preIg if prevously in game 
  */
  void setPrevousInGame(boolean preIg);
  /**Set the current leader board
  @param newLeaderBoard the new leader board
  */
  void setLeaderBoard(LeaderBoard newLeaderBoard);
  /**Set the root path of the level
  @param path the new root path of the level
  */
  void setRootPath(String path);
  /**Set if the current level has been completed
  @param complete If the current level has been completed
  */
  void setLevelComplete(boolean complete);
  /**Set the current level
  @param l The new level
  */
  void setLevel(Level l);
  /**Get if this client has reached the end of the current level
  @return true if this client has reached the end of the level
  */
  boolean getEndReached();
  /**Generate a random number
  @param start the lower bound of the number
  @param end the upper bound of the number
  @return the random number
  */
  float random(int start,int end);
  /**Get the root path of the level
  @return the root path of the level
  */
  String getRootPath();
  /**Get the current selected level info
  @return the selected level info
  */
  SelectedLevelInfo getMultyplayerSelectedLevel();
  /**Get the number of milliseconds since the program started
  @return milliseconds since the game launched
  */
  int millis();
  /**Save bytes to a file
  @param file the name of the file
  @param data the data to save to the file
  */
  void saveBytes(String file, byte[] data);
}
