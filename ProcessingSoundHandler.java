import processing.core.*;
import processing.sound.*;
import java.util.ArrayList;

/**Implmentation of the sound handler using processing's sound library as the audio back end.
*/
public class ProcessingSoundHandler extends SoundHandler{

  /**Create a new sound handler using processing's sound library for the back end, with the given music tracks and global sound and narration files.<br>
  Handles the porcess of actually loading the sound files
  @param musicFiles A 2D array of file paths to the muisc files for each track
  @param soundsFiles An array of file paths to the sound files
  @param narrationFiles An array of file paths to the narration files
  @param X A reffence to the surface the sounds will be played from
  */
  protected ProcessingSoundHandler(String[][] musicFiles, String[] soundsFiles, String[] narrationFiles,PApplet X) {
    System.out.println("Creating processing based sound handler");
    music=new SoundFile[musicFiles.length][];
    for (int i=0; i<musicFiles.length; i++) {//set the size of the music tracks
      music[i]=new SoundFile[musicFiles[i].length];
    }
    queue = new SoundFile[8];//create the sound queue
    sounds=new SoundFile[soundsFiles.length];
    for (int i =0; i<soundsFiles.length; i++) {//load the included game sounds
      sounds[i]=new SoundFile(X, soundsFiles[i]);
    }
    ggn=X;//set the parent application to run sounds through

    for (int i =0; i<musicFiles.length; i++) {
      for (int j=0; j<musicFiles[i].length; j++) {
        music[i][j]=new SoundFile(X, musicFiles[i][j]);//load the music files
      }
    }
    narrations = new SoundFile[narrationFiles.length];
    for(int i=0;i<narrations.length;i++){
      narrations[i] = new SoundFile(X,narrationFiles[i]);//load the narrations
    }
  }
  
  /**The music audio data
  */
  private SoundFile[] music[];
  /**The current sound queue
  */
  private SoundFile[] queue;
  /**The sound audio data
  */
  private SoundFile[] sounds;
  /**The narrtaion audio data
  */
  private SoundFile[] narrations;
  /**Currently playing sound buffer
  */
  private SoundFile []cSound=new SoundFile[3];
  /**The window to play the sound through
  */
  private PApplet ggn;
  /**Dynamcialy added level sound data
  */
  private ArrayList<SoundFile> levelSounds = new ArrayList<>();
  /**Dynamicaly added level narration data
  */
  private ArrayList<SoundFile> levelNarrations = new ArrayList<>();
  /**If the current music track should be chanegd by the sound thread
  */
  private boolean switchMusicTrack=false;
  /**If sounds are currenly enabled
  */
  private boolean enableSounds=false;
  /**If music should be started
  */
  private boolean startMusic=false;
  /**The current music file being played
  */
  private int musNum=0;
  /**The current music track plaing
  */
  private int currentMusicTrack=0;
  /**The music track that should be switched to
  */
  private int trackToSwitchTo=0;
  
  private float prevVol = 0;
  
  protected void tick() {
    if (enableSounds) {//if sounds are enabled right now
      if (startMusic) {//if the music should be started
        music[currentMusicTrack][musNum].play(1, getMasterVolume()*getMusicVolume());//play the next music track
         music[currentMusicTrack][musNum].amp(getMasterVolume()*getMusicVolume());//fix for sound lib being broken
        startMusic=false;
      }

      if (prevVol != getMasterVolume() * getMusicVolume()) {//if the volume changed
        music[currentMusicTrack][musNum].amp(getMasterVolume() * getMusicVolume());//change the volume of the currently playing music track
        prevVol=getMasterVolume() * getMusicVolume();
        if (getMasterVolume() * getMusicVolume()==0) {//if the new volume is 0
          music[currentMusicTrack][musNum].stop();//stop the music (so the console does not get spammed with warnings)
        }
      }

      if (!music[currentMusicTrack][musNum].isPlaying()&& getMasterVolume() * getMusicVolume() != 0) {//if the current song has ended
        musNum++;//switch to the next song
        if (musNum==music[currentMusicTrack].length){//if rached the end of the track go back to the start
          musNum=0;
        }
        music[currentMusicTrack][musNum].play(1, getMasterVolume() * getMusicVolume());//play the music
        //there appears to be a bug in the audio librarie that prevents passing the volume as a parameter in play from working
        //so we will manualy set the volume imedatly after
        music[currentMusicTrack][musNum].amp(getMasterVolume() * getMusicVolume());
        //what is weird is that play just calls the amp method under the hood 
      }

      playSound(cSound, 0);//do sound slot 1
      playSound(cSound, 1);//do sound slot 2
      playSound(cSound, 2);//do sound slot 3


      //hanle switching music tracks
      //just one of the features of the sound handler we do not currently use, why did i devlop all of this?
      if (switchMusicTrack && trackToSwitchTo != currentMusicTrack) {//if switcing track and the track to switch to is not the current track
        if (trackToSwitchTo>=0 && trackToSwitchTo<music.length) {//bounds check
          music[currentMusicTrack][musNum].stop();//stop the music on the current track
          currentMusicTrack=trackToSwitchTo;//switch the track to the new one
          music[currentMusicTrack][musNum].play(1, getMasterVolume() * getMusicVolume());//play the music on the other track
          music[currentMusicTrack][musNum].amp(getMasterVolume() * getMusicVolume());//stupid volume fix
        }
      }
    }
  }
  
  public void addToQueue(int soundNum) {
    SoundFile sound;
    if (soundNum<sounds.length) {//if the id is in the range of the global sunds
      sound=sounds[soundNum];//set the sound to the global sound
    } else {//if the id was in the grane of level specific sounds
      sound=levelSounds.get(soundNum-sounds.length);//set the sound to the specific level sound
    }
    //find the correct place in the queue to put the sound
    //note to self, use an actual queue structure for this in the future
    if (queue[0]==null) {
      queue[0]=sound;
      return;
    }
    if (queue[1]==null) {
      queue[1]=sound;
      return;
    }
    if (queue[2]==null) {
      queue[2]=sound;
      return;
    }
    if (queue[3]==null) {
      queue[3]=sound;
      return;
    }
    if (queue[4]==null) {
      queue[4]=sound;
      return;
    }
    if (queue[5]==null) {
      queue[5]=sound;
      return;
    }
    if (queue[6]==null) {
      queue[6]=sound;
      return;
    }
    if (queue[7]==null) {
      queue[7]=sound;
      return;
    }
    //if no place in the queue was found then lets just pretend that was never queued
  }
  
  /**Check if a sound is done in the currently playing slot
  @param R The sound to check
  */
  private boolean moveUp(SoundFile R) {
    if (R==null){//if the sound is null then return true, null can be moved up
      return true;
    }
    if (!R.isPlaying()){//if the sound is not playing then return true, it can be moved up
      return true;
    }
    //if it is playing then it can not be moved up
    return false;
  }
  
  /**Try to play a sound
  @param R The list of currently playing sounds
  @param n The index of the slot to process playing for
  */
  private void playSound(SoundFile[] R, int n) {
    if (moveUp(R[n])) {//if this slot is readdy to get the next sound

      R[n]=queue[0];//grab the first element from the queue
      if (R[n]!=null){//if something was grabbed
        if (getMasterVolume() * getSoundsVolume() != 0){//if the sound is turned on
          R[n].play(1, getMasterVolume() * getSoundsVolume());//play the sound
          R[n].amp(getMasterVolume() * getSoundsVolume());//sound lib is broken so this is nessary to deal with the volume
        }
      }
      //move all items in the queue up by 1
      for (int i =0; i<7; i++) {
        queue[i]=queue[i+1];
      }
      queue[7]=null;//empty the last slot in the queue
    }
  }
  
  public void setMusicTrack(int track) {
    trackToSwitchTo=track;
    switchMusicTrack=true;
  }
  
  public void startSounds() {
    enableSounds=true;
    startMusic=true;
  }
  
  public void stopSounds() {
    enableSounds=false;
    music[currentMusicTrack][musNum].pause();
  }
  
  public int registerLevelSound(String path) {
    if(path.endsWith(".wav")){
        Util.validateWavFileBitRate(path);
      }
    SoundFile sound = new SoundFile(ggn, path);
    int id =sounds.length+levelSounds.size();
    levelSounds.add(sound);
    return id;
  }
  
  public int registerLevelNarration(String path){
    if(path.endsWith(".wav")){
        Util.validateWavFileBitRate(path);
      }
    SoundFile sound = new SoundFile(ggn, path);
    int id = narrations.length+levelNarrations.size();
    levelNarrations.add(sound);
    return id;
  }
  
  public boolean isPlaying(int n) {
    if (n<sounds.length) {//check for the global sound range
      return sounds[n].isPlaying();
    } else {
      return levelSounds.get(n-sounds.length).isPlaying();
    }
  }
  
  public boolean isInQueue(int n) {
    SoundFile s;
    if (n<sounds.length) {
      s= sounds[n];
    } else {
      s= levelSounds.get(n-sounds.length);
    }
    for (int i=0; i<queue.length; i++) {
      if (queue[i]!=null&&s.equals(queue[i])) {
        return true;
      }
    }
    return false;
  }
  
  public void cancleSound(int n) {
    SoundFile s;
    //get the sound
    if (n<sounds.length) {
      s = sounds[n];
    } else {
      s= levelSounds.get(n-sounds.length);
    }
    //check if it is currently playing
    if (s.isPlaying()) {
      //if so stop it
      s.stop();
      return;
    }
    //go through the queue and check for the sounds
    for (int i=0; i<queue.length; i++) {
      if (queue[i]!=null&&s.equals(queue[i])) {
        //removeing it if it is found
        queue[i]=null;
        return;
      }
    }
  }
  
  public void playNarration(int n){
    SoundFile sound;
    //get the narration
    if (n<narrations.length) {
      sound=narrations[n];
    } else {
      sound=levelNarrations.get(n-narrations.length);
    }
    //play it
    sound.play(1, getMasterVolume() * getNarrationVolume());
    sound.amp(getMasterVolume() * getNarrationVolume());
  }
  
  public boolean isNarrationPlaying(int n){
    if (n<narrations.length) {
      return narrations[n].isPlaying();
    } else {
      return levelNarrations.get(n-narrations.length).isPlaying();
    }
  }
  
  public boolean anyNarrationPlaying(){
    for(SoundFile s: narrations){
      if(s.isPlaying()){
        return true;
      }
    }
    for(SoundFile s: levelNarrations){
      if(s.isPlaying()){
        return true;
      }
    }
    return false;
  }
  
  public void stopNarration(int n){
    SoundFile s;
    if (n<narrations.length) {
      s= narrations[n];
    } else {
      s= levelNarrations.get(n-narrations.length);
    }

    if (s.isPlaying()) {
      s.stop();
      return;
    }
  }
  
  public void dumpLS() {//dump level sounds and allow them to be garbage collected
    for (int i=0; i<levelSounds.size(); i++) {//go through the level sounds
      levelSounds.get(i).removeFromCache();
    }
    for(int i=0;i<levelNarrations.size();i++){//go through the level narrations
      levelNarrations.get(i).removeFromCache();
    }
    //reset both of the array
    levelSounds = new ArrayList<>();
    levelNarrations = new ArrayList<>();
    System.gc();//run garbage collection to remove old unloaded sound files from memory
  }
  
  
}
