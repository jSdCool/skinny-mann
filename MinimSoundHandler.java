import processing.core.*;
import ddf.minim.*;
import java.util.ArrayList;
/**Implmentation of the sound handler using minim as the audio back end.
*/
public class MinimSoundHandler extends SoundHandler{
  
  /**Create a new sound handler with the given music tracks and global sound and narration files.<br>
  Handles the porcess of actually loading the sound files
  @param musicFiles A 2D array of file paths to the muisc files for each track
  @param soundsFiles An array of file paths to the sound files
  @param narrationFiles An array of file paths to the narration files
  @param X A reffence to the surface the sounds will be played from
  */
  public MinimSoundHandler(String[][] musicFiles, String[] soundsFiles, String[] narrationFiles,PApplet X){
    minim = new Minim(X);
    music=new AudioPlayer[musicFiles.length][];
    for (int i=0; i<musicFiles.length; i++) {//set the size of the music tracks
      music[i]=new AudioPlayer[musicFiles[i].length];
    }
    queue = new AudioPlayer[8];//create the sound queue
    sounds=new AudioPlayer[soundsFiles.length];
    for (int i =0; i<soundsFiles.length; i++) {//load the included game sounds
      sounds[i]=minim.loadFile(soundsFiles[i]);
    }

    for (int i =0; i<musicFiles.length; i++) {
      for (int j=0; j<musicFiles[i].length; j++) {
        music[i][j]=minim.loadFile(musicFiles[i][j]);//load the music files
      }
    }
    narrations = new AudioPlayer[narrationFiles.length];
    for(int i=0;i<narrations.length;i++){
      narrations[i] = minim.loadFile(narrationFiles[i]);//load the narrations
    }
  }
  
  /**The sound system manager
  */
  private Minim minim;
  /**The music audio data
  */
  private AudioPlayer[] music[];
  /**The current sound queue
  */
  private AudioPlayer[] queue;
  /**The sound audio data
  */
  private AudioPlayer[] sounds;
  /**The narrtaion audio data
  */
  private AudioPlayer[] narrations;
  /**Currently playing sound buffer
  */
  private AudioPlayer []cSound=new AudioPlayer[3];//sound queue
  /**Dynamcialy added level sound data
  */
  private ArrayList<AudioPlayer> levelSounds = new ArrayList<>();
  /**Dynamicaly added level narration data
  */
  private ArrayList<AudioPlayer> levelNarrations = new ArrayList<>();
  /**The current music file being played
  */
  private int musNum=0;
  /**The current music track plaing
  */
  private int currentMusicTrack=0;
  /**The music track that should be switched to
  */
  private int trackToSwitchTo=0;
  /**The prevous music volume
  */
  private float prevVol=1;
  /**If the current music track should be chanegd by the sound thread
  */
  private boolean switchMusicTrack=false;
  /**If sounds are currenly enabled
  */
  private boolean enableSounds=false;
  /**If music should be started
  */
  private boolean startMusic=false;
  
  @Override
  protected void tick() {
    if (enableSounds) {//if sounds are enabled right now
      if (startMusic) {//if the music should be started
        music[currentMusicTrack][musNum].rewind();
        music[currentMusicTrack][musNum].play();
        music[currentMusicTrack][musNum].setGain(linearToDb(getMasterVolume()*getMusicVolume()));//play the next music track
        startMusic=false;
      }

      if (prevVol != getMasterVolume()*getMusicVolume()) {//if the volume changed
        music[currentMusicTrack][musNum].setGain(linearToDb(getMasterVolume()*getMusicVolume()));
        //music[currentMusicTrack][musNum].amp(masterVolume*musicVolume);//change the volume of the currently playing music track
        prevVol = getMasterVolume()*getMusicVolume();
        if (getMasterVolume()*getMusicVolume() == 0) {//if the new volume is 0
          music[currentMusicTrack][musNum].pause();//stop the music (so the console does not get spammed with warnings)
          music[currentMusicTrack][musNum].rewind();
        }
      }

      if (!music[currentMusicTrack][musNum].isPlaying() && getMasterVolume()*getMusicVolume() != 0) {//if the current song has ended
        musNum++;//switch to the next song
        if (musNum==music[currentMusicTrack].length){//if rached the end of the track go back to the start
          musNum=0;
        }
        music[currentMusicTrack][musNum].rewind();
        music[currentMusicTrack][musNum].play();
        music[currentMusicTrack][musNum].setGain(linearToDb(getMasterVolume() * getMusicVolume()));//play the music
        //there appears to be a bug in the audio librarie that prevents passing the volume as a parameter in play from working
        //so we will manualy set the volume imedatly after
        //what is weird is that play just calls the amp method under the hood 
      }

      playSound(cSound, 0);//do sound slot 1
      playSound(cSound, 1);//do sound slot 2
      playSound(cSound, 2);//do sound slot 3


      //hanle switching music tracks
      //just one of the features of the sound handler we do not currently use, why did i devlop all of this?
      if (switchMusicTrack && trackToSwitchTo != currentMusicTrack) {//if switcing track and the track to switch to is not the current track
        if (trackToSwitchTo>=0 && trackToSwitchTo<music.length) {//bounds check
          music[currentMusicTrack][musNum].pause();//stop the music on the current track
          currentMusicTrack=trackToSwitchTo;//switch the track to the new one
          music[currentMusicTrack][musNum].rewind();
          music[currentMusicTrack][musNum].play();//play the music on the other track
          music[currentMusicTrack][musNum].setGain(linearToDb(getMasterVolume() * getMusicVolume()));//stupid volume fix
        }
      }
    }
  }
  
  /**Add a sound to the sound queue
  @param soundNum The numberical Id of the sound to play
  */
  @Override
  public void addToQueue(int soundNum) {
    AudioPlayer sound;
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
  private boolean moveUp(AudioPlayer R) {
    if (R==null){//if the sound is null then return true, null can be moved up
      return true;
    }
    if (!R.isPlaying()){//if the sound is not playing then return true, it can be moved up
      return true;
    }
    //if it is playing then it can not be moved up
    return false;
  }
  
  /**Convert the percentage volume value to a deciable baced value
  @param value The percent based volume value
  @return A decibel based volume value
  */
  private float linearToDb(float value) {
    if (value <= 0.0001f) return -80; // effectively silent
      return 20f * (float)Math.log10(value);
  }
  
  /**Try to play a sound
  @param R The list of currently playing sounds
  @param n The index of the slot to process playing for
  */
  private void playSound(AudioPlayer[] R, int n) {
    if (moveUp(R[n])) {//if this slot is readdy to get the next sound

      R[n]=queue[0];//grab the first element from the queue
      if (R[n]!=null){//if something was grabbed
        if (getMasterVolume() * getSoundsVolume() != 0){//if the sound is turned on
          R[n].rewind();
          R[n].play();
          R[n].setGain(linearToDb(getMasterVolume() * getSoundsVolume()));
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
    AudioPlayer sound = minim.loadFile(path);
    int id =sounds.length+levelSounds.size();
    levelSounds.add(sound);
    return id;
  }

  public int registerLevelNarration(String path){
    AudioPlayer sound = minim.loadFile(path);
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
    AudioPlayer s;
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
    AudioPlayer s;
    //get the sound
    if (n<sounds.length) {
      s = sounds[n];
    } else {
      s= levelSounds.get(n-sounds.length);
    }
    //check if it is currently playing
    if (s.isPlaying()) {
      //if so stop it
      s.pause();
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
    AudioPlayer sound;
    //get the narration
    if (n<narrations.length) {
      sound=narrations[n];
    } else {
      sound=levelNarrations.get(n-narrations.length);
    }
    //play it
    sound.rewind();
    sound.play();
    sound.setGain(linearToDb(getMasterVolume() * getNarrationVolume()));
  }
  
  public boolean isNarrationPlaying(int n){
    if (n<narrations.length) {
      return narrations[n].isPlaying();
    } else {
      return levelNarrations.get(n-narrations.length).isPlaying();
    }
  }
  
  public boolean anyNarrationPlaying(){
    for(AudioPlayer s: narrations){
      if(s.isPlaying()){
        return true;
      }
    }
    for(AudioPlayer s: levelNarrations){
      if(s.isPlaying()){
        return true;
      }
    }
    
    return false;
  }
  
  public void stopNarration(int n){
    AudioPlayer s;
    if (n<narrations.length) {
      s= narrations[n];
    } else {
      s= levelNarrations.get(n-narrations.length);
    }

    if (s.isPlaying()) {
      s.pause();
      return;
    }
  }
}
