//version 2.1.0
import processing.core.*;
import java.util.ArrayList;
import java.util.HashMap;
/**The game's sound engine and sound loader
*/
public abstract class SoundHandler extends Thread {
  
  private float masterVolume=1, musicVolume=1, sfxVolume=1, narrationVolume=1;
  private boolean keepAlive=true;
  
  private static SoundHandler instance;
  
  private static HashMap<String, SoundHandlerFactory> backends = new HashMap<>();
  
  static {
    backends.put("processing", ProcessingSoundHandler::new);
    backends.put("minim", MinimSoundHandler::new);
  }

  ///**Create a new sound handler with the given music tracks and global sound and narration files.<br>
  //Handles the porcess of actually loading the sound files
  //@param musicFiles A 2D array of file paths to the muisc files for each track
  //@param soundsFiles An array of file paths to the sound files
  //@param narrationFiles An array of file paths to the narration files
  //@param X A reffence to the surface the sounds will be played from
  //*/
  //private SoundHandler(String[][] musicFiles, String[] soundsFiles, String[] narrationFiles,PApplet X)
  
  /**Default constructor for the sound handler base, sets the instance and starts the thread!
  */
  protected SoundHandler(){
    instance = this;
    start();//start the independednt sound handler thread
  }

  /**The thread the sound handler runs on.
  DO NOT OVERRIDE!!
  */
  public void run() {
    try {
      while (keepAlive) {//while thw sound handler should be running
        tick();
        Thread.sleep(10);//wait 10ms before handling things again. This lowers CPU useage and prevent the audio from being studdery from over interation
      }
    } catch(Exception i) {
      System.out.println("the sound handler ran into an error");
      i.printStackTrace();
      throw new RuntimeException("Soundhanlder ran into an error!",i);
    }
  }
  
  /**Process a single tick of the sound handler, This is what actually handels the sounds
  */
  abstract protected void tick();

  /**Add a sound to the sound queue
  @param soundNum The numberical Id of the sound to play
  */
  public abstract void addToQueue(int soundNum);
  
  /**Set the master volume.<br>
  For some reason I have been too lazy to implment a setting to adjust this
  @param volume The new master volume
  */
  public void setMasterVolume(float volume) {
    masterVolume=volume;
  }
  /**Set the music volume
  @param volume The new music volume
  */
  public void setMusicVolume(float volume) {
    musicVolume=volume;
  }
  /**Set the sound volume
  @param volume The new sound volume
  */
  public void setSoundsVolume(float volume) {
    sfxVolume=volume;
  }
  /**Set the narration volume
  @param volume the new narration volume
  */
  public void setNarrationVolume(float volume){
    narrationVolume = volume;
  }
  /**Get the current master volume
  @return The current master volume
  */
  public float getMasterVolume(){
    return masterVolume;
  }
  /**Get the current volume music will play at
  @return The volume music will play at
  */
  public float getMusicVolume(){
    return musicVolume;
  }
  /**Get the volume sounds will play at
  @return The volume sounds will play at
  */
  public float getSoundsVolume(){
    return sfxVolume;
  }
  /**Get the volume narrations will play at
  @return the volume narrations will play at
  */
  public float getNarrationVolume(){
    return narrationVolume;
  }
  
  /**Create a new sound handler builder
  @param a The surface to build the sound handler for
  */
  public static Builder builder(PApplet a) {
    return new Builder(a);
  }
  
  /**Change the music track to a diffrent one
  @param track The new music track index to switch to
  */
  public abstract void setMusicTrack(int track);

  /**Start the sound handler, this must be done after creating the soud hander
  */
  public abstract void startSounds();

  /**Stop the sound handler from playing any new sounds and pause any currenly playing music
  */
  public abstract void stopSounds();

  /**Register a new level sound
  @param path The path to the sound file
  @return The id of the newly registered sound
  */
  public abstract int registerLevelSound(String path);

  /**Register a new level narration sound
  @param path The path to the sound file
  @return The id of the newly registered narration
  */
  public abstract int registerLevelNarration(String path);

  /**Check if a sound is currently playing
  @param n The id of the sound to check
  @return true if the specified sound is playing
  */
  public abstract boolean isPlaying(int n);

  /**Check if a sound is currently in the sound queue
  @param n The id of the sound to check
  @return true if the specified sound is in the sound queue
  */
  abstract public boolean isInQueue(int n);

  /**Remove a sound from the queue before it is played
  @param n The id of the sound to remove
  */
  abstract public void cancleSound(int n);
  
  /**Play the given narration
  @param n The id of the narration to play
  */
  abstract public void playNarration(int n);

  /**Check if a narration is currently playing
  @param n The id of the narration to check
  @return true if the specified narration is playing
  */
  abstract public boolean isNarrationPlaying(int n);

  /**check if any narrations are playing
  @return true if any narration is playing
  */
  abstract public boolean anyNarrationPlaying();

  /**Stops the given narrtation if it is playing
  @param n The id of the narration to stop
  */
  abstract public void stopNarration(int n);
  
  /**Unload all registerd level sounds and narrations. Removing them from the cashe allowing them to be grabage collected.<br>
  */
  public void dumpLS(){}
  
  /**Get the current instance of the sound handler
  @return The exsisting sound handler
  */
  public static SoundHandler getInstance(){
    return instance;
  }

  //===============================BUILDER===============================
  
  /**Builder class used to create a new sound handler. Also used to be able to progrmaitaclly add global sounds to the sound handler
  */
  public static class Builder {
    /**Create a new sound hander builder
    @param a The surface to play the sounds on
    */
    private Builder(PApplet a) {
      window=a;
      musicPaths.add(new ArrayList<String>());
    }
    /**The parent window
    */
    private PApplet window;
    /**The file paths for all the music files
    */
    private ArrayList<ArrayList<String>> musicPaths=new ArrayList<>();
    /**The file paths for all the sounds
    */
    private ArrayList<String> soundPaths=new ArrayList<>();
    /**The file paths for all the narrations
    */
    private ArrayList<String> narrationPaths = new ArrayList<>();
    /**the number of music tracks (not number of songs)
    */
    private int numMusicTracks=1;
    /**The current backend factory
    */
    private SoundHandlerFactory factory = backends.get("processing");//default to processing (linux should default to minim)
    /**Add a music file to the builder
    @param path The path of the sound file
    @param track The track to put this song in
    @return this
    */
    public Builder addMusic(String path, int track) {
      if (track>=numMusicTracks||track<0)
        throw new RuntimeException("invalid music track number "+track);
      musicPaths.get(track).add(path);
      return this;
    }
    /**Add a sound file to the builder
    @param path The path of the sound file
    @return this
    */
    public Builder addSound(String path) {
      soundPaths.add(path);
      return this;
    }
    /**Add a narration file to the builder
    @param path The path of the sound file
    @param narrationIdCallBack An array of size 1 where the first element will be set the the numberical id of the registerd narration
    @return this
    */
    public Builder addNarration(String path,int[] narrationIdCallBack){
      if(narrationIdCallBack != null && narrationIdCallBack.length >0){
        narrationIdCallBack[0] = narrationPaths.size();
      }
      narrationPaths.add(path);
      return this;
    }
    /**Add a narration file to the builder
    @param path The path of the sound file
    @return this
    */
    public Builder addNarration(String path){
      return addNarration(path,null);
    }
    /**Add a new music track to the builfer
    @return this
    */
    public Builder addMusicTrack() {
      numMusicTracks++;
      musicPaths.add(new ArrayList<String>());
      return this;
    }
    /**Get the number of tracks in this builder
    @return The number of music tracks this builder has right now
    */
    public int getNumTracks() {
      return numMusicTracks;
    }
    
    /**Set the backend that should be used for the sound handler
    @param requestedBackEnd The name of the back end that should be used
    @return this
    */
    public Builder setBackend(String requestedBackEnd){
      SoundHandlerFactory newBackend = backends.get(requestedBackEnd);
      if(newBackend != null){
        factory = newBackend;
      } else {
        throw new RuntimeException("Unknown Sound Backend: "+requestedBackEnd);
      }
      return this;
    }
    
    /**Build the sound handler from the provided information
    @return The new sound hander object
    */
    public SoundHandler build() {
      String[] sounds=soundPaths.toArray(new String[]{});
      String[][] music=new String[numMusicTracks][];
      String[] narrations = narrationPaths.toArray(new String[]{});
      for (int i=0; i<numMusicTracks; i++) {
        music[i]=musicPaths.get(i).toArray(new String[]{});
      }

      return factory.create(music, sounds, narrations, window);
    }
  } // end of builder
  
  /**Interface used to dynamicaly create sound handler backend varients 
  */
  public interface SoundHandlerFactory{
    /**Create a new sound handler with the given music tracks and global sound and narration files.<br>
    Handles the porcess of actually loading the sound files
    @param musicFiles A 2D array of file paths to the muisc files for each track
    @param soundsFiles An array of file paths to the sound files
    @param narrationFiles An array of file paths to the narration files
    @param X A reffence to the surface the sounds will be played from
    @return A new instance of the requested sound handler backend 
    */
    SoundHandler create(String[][] musicFiles, String[] soundsFiles, String[] narrationFiles,PApplet X);
  }
}
