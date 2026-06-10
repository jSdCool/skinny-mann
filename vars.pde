//start of vars.pde
//this file is just a mass of global vars
/**The shader that handles the "realistic" shadow generation
*/
PShader shadowShader;
/**The depth buffer shader used for shadow generation
*/
PShader depthBufferShader;
/**The output depth buffer used for generating shadows
*/
PGraphics shadowMap;
/**Used to get correct sub camera matix without rendering anything
*/
PGraphics cameraMatrixMap;
/**Sub sections of the regular depth buffer shadow map
*/
PGraphics[] subShadowMaps = new PGraphics[4];

/**A uv test image
*/
PImage uvTester;
/**The startup logo
*/
PImage CBi;
/**The red movement arrow from the level creator
*/
PShape redArrow;
/**The green movemnt arrow from the level creaor
*/
PShape greenArrow;
/**The blue movement arrow from the level creator
*/
PShape blueArrow;
/**The selected movevemnt arrow from the level creator
*/
PShape yellowArrow;
/**The red scaler from the level creator
*/
PShape redScaler;
/**The green scaler from the level creator
*/
PShape greenScaler;
/**The blue scaler from the level creator
*/
PShape blueScaler;
/**The selected scaler from the level creator
*/
PShape yellowScaler;
/**The level creator logo from the level creators main menu
*/
PShape LevelCreatorLogo;
/**The red circle from the roatoation gismo
*/
PShape rotateCircleX;
/**The Green circle from the rotation gismo
*/
PShape rotateCircleY;
/**The blue curcle from the rotation gismo
*/
PShape rotateCircleZ;
/**The yellow selected curcle from the roation gismo
*/
PShape rotateCircleHilight;

/**Used to request the render thread preform an initialization of the depth buffer
*/
boolean requestDepthBufferInit = false ;
/**If the raw depth buffer should be renderd to the screen
*/
boolean showDepthBuffer = false;
/**If the shadow shader should render the extracted depth values instead of the regular colors
*/
boolean shadowShaderOutputSampledDepthInfo = false;
/**If the game is currently in a menu
*/
boolean menue = true;
/**If the game is currently in a level
*/
boolean inGame = false;
/**If the player is holding the D key down
*/
boolean player1_moving_right = false;
/**If the player is holding the A key down
*/
boolean player1_moving_left = false;
//=================== DEV MODE HERE =================
/**If developer debugging and shortcuts should be enabled (false in release builds)
*/
boolean dev_mode = true;
/**If the player is holding down the SPACE bar
*/
boolean player1_jumping = false;
/**If the player is currently dead
*/
boolean dead = false;
/**If the player has completed the current level
*/
boolean level_complete = false;
/**Used to request the players spawn point be reset to the current location
*/
boolean reset_spawn = false;
/**If the player has recently pressed the E key
*/
boolean E_pressed = false;
/**If the physics thread should continue to run
*/
boolean loopThread2 = true;
/**Used to determine wether the setting menu should go back to the game or main menu on exit
*/
boolean prevousInGame = false;
/**Used to request the player be moved to the location specified by the setPlayerPos{X Y Z} variables 
*/
boolean setPlayerPosTo = false;
/**If the current level should render in 3D mode
*/
boolean e3DMode = false;
/**Set but never tested
*/
boolean checkpointIn3DStage = false;
/**If the player is pressing the W key
*/
boolean WPressed = false;
/**If the player is pressing the S key
*/
boolean SPressed = false;
/**If the level completed sound has been played yet at the end of the level
*/
boolean levelCompleteSoundPlayed = false;
/**If currently in the tutorial
*/
boolean tutorialMode = false;
/**If the current level is a UGC level
*/
boolean UGC_lvl = false;
/**If the current level has been completed
*/
boolean levelCompatible = false;
/**If currently editing a blueprint
*/
boolean editingBlueprint = false;
/**If currently viewing a compoenent's content
*/
boolean viewingItemContents = false;
/**If the selection tool is currently in use
*/
boolean selecting = false;
/**If the S key is currently pressed for the 3D camera
*/
boolean s3D = false;
/**If the W key is currently pressed for the 3D camera
*/
boolean w3D = false;
/**If the shift key is currenly pressed for the 3D cmaera
*/
boolean shift3D = false;
/**If the SPACE bar is currenly pressed for the 3D camera
*/
boolean space3D = false;
/**If the D key is currenly pressed for the 3D camera
*/
boolean d3D = false;
/**If the A key is currenly pressed for the 3D camera
*/
boolean a3D = false;
/**If the pan camera down key is pressed
*/
boolean cam_down = false;
/**If the pan camera up key is pressed
*/
boolean cam_up = false;
/**If the pan camera right key is pressed
*/
boolean cam_right = false;
/**If the pan camera left key is pressed
*/
boolean cam_left = false;
/**If the current player is hosting the multyplayer session
*/
boolean isHost = false;
/**If currenly in multyplayer
*/
boolean multiplayer = false;
/**If the client is quitting the multyplayer game
*/
boolean clientQuitting = false;
/**If the currently waiting for the clients in multyplayer
*/
boolean waitingForReady = false;
/**If the current level has finished loading
*/
boolean loaded = false;
/**If the client has reached the end of a CO OP level
*/
boolean reachedEnd = false;
/**If currently editing a stage
*/
boolean editingStage = false;
/**If the game should fully process physics in the level creator
*/
boolean simulating = false;
/**If using the deleteing tool
*/
boolean deleteing = false;
/**If using the moving player tool
*/
boolean moving_player = false;
/**If grid mode is is use
*/
boolean grid_mode = false;
/**If using the portal tool
*/
boolean drawingPortal = false;
/**If using the blueprint tool
*/
boolean selectingBlueprint = false;
/**If editing a logic board
*/
boolean editinglogicBoard = false;
/**If using the connection tool
*/
boolean connectingLogic = false;
/**If using the move logic tool
*/
boolean moveLogicComponents = false;
/**If the current saved colors are requesting saving
*/
boolean saveColors = false;
/**If currenly on the level overview page
*/
boolean levelOverview = false;
/**If in the second half of the potal placement process
*/
boolean drawingPortal3 = false;
/**If setting the inital level spawn
*/
boolean settingPlayerSpawn = false;
/**If the level creator is currenly active
*/
boolean levelCreator = false;
/**If the current draggable component is being dragged
*/
boolean drawing = false;
/**Place the component that us currely active
*/
boolean draw = false;
/**Delet the thing the mouse is over
*/
boolean delete = false;
/**If the axis of translation is the x asis 
*/
boolean translateXaxis = false;
/**If the axis of translation is the y asis 
*/
boolean translateYaxis = false;
/**If the axis of translation is the z asis 
*/
boolean translateZaxis = false;
/**The stage selection part of the portal placement process
*/
boolean drawingPortal2 = false;
/**If on the inital logo
*/
boolean startup = false;
/**If on the load level levelcreator screen
*/
boolean loading = false;
/**If on the new level screen
*/
boolean newLevel = false;
/**If on the new level fine screen
*/
boolean newFile = false;
/**If on the create new blueprint screen
*/
boolean creatingNewBlueprint = false;
/**If on the load blueprint screen
*/
boolean loadingBlueprint = false;
/**If the typing cursor is curretly visable
*/
boolean coursor = false;
/**If connecting logic components
*/
boolean connecting = false;
/**If movinf a logic component
*/
boolean movingLogicComponent = false;
/**If on the exti level creator confirm screen
*/
boolean exitLevelCreator = false;
/**If the level to load was not found
*/
boolean levelNotFound = false;
/**If currently animating a menu transiton 
*/
boolean transitioningMenu = false;
/**If the new sound being added should be a narration
*/
boolean newSoundAsNarration = false;
/**If the new blueprint to be created should be a 3D blueprint
*/
boolean newBlueprintIs3D = false;
/**Unused
*/
boolean placingGoon = false;
/**If the rotation tool is currenly in use
*/
boolean rotating = false;
/**Used to request the set FPS be updated by the render thread
*/
boolean updateFPS = false;
/**A list that gets populated with wther each UGC level is comptable with the current game version
*/
ArrayList<Boolean> compatibles;
/**The current state of all coins in the current level.
Strange this is not contined in the level, oh well
*/
ArrayList<Boolean> coins;

/**Vector representing the direction of 3D light used for shadow calculation
*/
PVector lightDir = new PVector();
/**Vector used to represent the rotation of the currenlty selected component
*/
PVector currentComponentRotation = new PVector();

/**What menu the game is currenly on
*/
String Menue = "creds";
/**The current version of the game
*/
String version = "0.10.2_Early_Access";
/**The current version of the level creator
*/
String EDITOR_version = "0.3.2_EAc";
/**The ip address the client is connecting to
*/
String ip = "localhost";
/**The name of this player
*/
String name = "can't_be_botherd_to_chane_it";
/**The specific settings sub menu curretly being displayed
*/
String settingsMenue = "game play";
/**The current set author for the level creator
*/
String author = "";
/**The large text to be displayed on the screen (Press E)
*/
String displayText = "";
/**Copy of the version
*/
String GAME_version = version;
/**Latested version of the game as obtained from the internet
*/
String internetVersion;
/**The typing cursor that can be appended to the end of visable text. currently unused but still functinal 
*/
String cursor = "";
/**The reason for disconnecting from multyplayer
*/
String disconnectReason = "";
/**The set of levels to dispaly in the multyplayer menu
*/
String multyplayerSelectionLevels = "speed";
/**The file path of the multyplayer level that is currenly being analyzed
*/
String multyplayerSelectedLevelPath;
/**The folder where user specific save or settings data can be stored. 
Set the the value of the enviorment varble `appdata` on windows and `HOME` on non windows systems.
*/
String appdata;
/**same as coursor. used in the level creastor for raw text input areas
*/
String coursorr = "";
/**The entered name of a new level / blueprint or one you want to load
*/
String new_name;
/**The type of the new level file being added
*/
String newFileType = "2D";
/**The path of the file to copy into the level
*/
String fileToCoppyPath = "";
/**The set default name of the level creator author
*/
String defaultAuthor = "can't be botherd to change it";
/**The names of all found UGC levels
*/
ArrayList<String> UGCNames = new ArrayList<>();
/**The names of all other players in the multyplayer session
*/
ArrayList<String> playerNames=new ArrayList<>();

//String[] musicTracks ={"data/music/track1.wav", "data/music/track2.wav", "data/music/track3.wav"};
//String[] sfxTracks={"data/sounds/level complete.wav"};
/**All game versions whose levels are compatable with this version of the fame
*/
String[] compatibleVersions={"0.10.0_Early_Access","0.10.1_Early_Access","0.10.2_Early_Access"};

/**How the drawn level component should be scaled
*/
float Scale;
/**gravity for physics in units of pixels / millisconds^2
*/
float gravity = 0.001;
/**The x position the mouse was pressed down while dragging out a level component
*/
float downX;
/**The y position the mouse was pressed down while dragging out a level component
*/
float downY;
/**The x position the mouse was released down while dragging out a level component
*/
float upX;
/**The y position the mouse was released down while dragging out a level component
*/
float upY;
/**The x coord at witch a 3D blueprint should be placed
*/
float blueprintPlacemntX;
/**The y coord at witch a 3D blueprint should be placed
*/
float blueprintPlacemntY;
/**The z coord at witch a 3D blueprint should be placed
*/
float blueprintPlacemntZ;

/**Coordinates the player should be teleported to
*/
float[] tpCords = new float[3];
/**The maximum coordinate for a blueprint used to position movemnt arrows
*/
float[] blueprintMax = new float[3];
/**The minimum coordinate for a blueprintused to position movement arrows
*/
float[] blueprintMin = new float[3];

/**The x position of the 2D camera
*/
int camPos = 0;
/**The y position of the 2D camera
*/
int camPosY = 0;
/**cool down untill you are not dead
*/
int death_cool_down;
/**unused - former use: wait on the startup logo before going to the main menu
*/
int start_down;
/**The port to use in multyplayer
*/
int port = 9367;
/**unused
*/
int scroll_left;
/**unused
*/
int scroll_right;
/**x position the player will respawn to
*/
int respawnX = 20;
/**y position the player will respawn to
*/
int respawnY = 700;
/**z position the player will respawn to
*/
int respawnZ = 150;
/**unused
*/
int spdelay = 0;
/**The index of the stage to respawn in
*/
int respawnStage;
/**unused
*/
int stageIndex;
/**The number of coins the plauer has collected
*/
int coinCount = 0;
/**x position to move the player to
*/
int setPlayerPosX;
/**y position to move the player to
*/
int setPlayerPosY;
/**z position to move the player to, never used
*/
int setPlayerPosZ;
/**when to show the glitch effect unitll
*/
int gmillis = 0;
/**the current angle 3D coins are rotated to
*/
int coinRotation = 0;
/**The index of the stage the player is currenly on 
*/
int currentStageIndex;
/**The index of the last stage component that should be drawn in the tutorial
*/
int tutorialDrawLimit = 0;
/**when to display text on the screen until
*/
int displayTextUntill = 0;
/**where in the tutorial the player is
*/
int tutorialPos = 0;
/**The current sound the tutorial is on
*/
int currentTutorialSound;
/**The index of the currently selcted UGC level
*/
int UGC_lvl_indx;
/**The index of the currently selected stage component
*/
int selectedIndex = -1;
/**The index of the stage component the player is currently viewing
*/
int viewingItemIndex = -1;
/**the x camera pos used in the render loop
*/
int drawCamPosX = 0;
/**the y camera pos used in the render loop
*/
int drawCamPosY = 0;
/**The index of the current player
*/
int currentPlayer = 0;
/**The number of players currently connected
*/
int currentNumberOfPlayers = 10;
/**The time this stage was started
*/
int startTime;
/**The best clear time acheved
*/
int bestTime = 0;
/**The length of time this speed run session is set for
*/
int sessionTime = 600000;
/**The time the current speed run session ends
*/
int timerEndTime;
/**For the simple 3D editor, the z value to use
*/
int startingDepth = 0;
/**For the simple 3D editor, the z depth to use
*/
int totalDepth = 300;
/**The size of the grid for grid mode
*/
int grid_size = 10;
/**The current mode for 3D transformation (move, scale
*/
int current3DTransformMode = 1;
/**The index of the blueprint currently being viewed
*/
int currentBluieprintIndex = 0;
/**The index of the logic board being viewed
*/
int logicBoardIndex = 0;
/**The color to use when placeing new stage components
*/
int Color = 0;
/**unused
*/
int RedPos = 0;
/**unused
*/
int BluePos = 0;
/**unused
*/
int GreenPos = 0;
/**The red component of the color being calcualted
*/
int RC = 0;
/**The green component of the color being calculated
*/
int GC = 0;
/**The blue component of the color being calculated 
*/
int BC = 0;
/**The current direction a triangle is being palced in
*/
int triangleMode = 0;
/**The specifc movement rod/scale rod that was clicked
*/
int transformComponentNumber = 0;
/**The stage the fisrt portal was placed on
*/
int preSI = 0;
/**The current thing selected in the level creator overview
*/
int overviewSelection = -1;
/**Where the level creator has been scrolled to
*/
int filesScrole = 0;
/**The index of the logic component that we started connecting from
*/
int connectingFromIndex = 0;
/**The index of teh logic component being moved
*/
int movingLogicIndex = 0;
/**The current progress in game loading
*/
int loadProgress = 0;
/**The total load progress points for when loading the game (i think there are 55 right now)
*/
int totalLoad = 55;
/**The time this physics frame started
*/
int curMills = 0;
/**The time the last physics frame started 
*/
int lasMills = 0;
/**The number of milliseconds the last physics cycle took (effectivly delta time)
*/
int mspc = 0;
/**The time of the last update to the set frame rate
*/
int lastFPSUpdate = 0;
/**The number of milliseconds the last frame took to render
*/
int lastFrameTime = 0;
/**3D camera x roataion angle
*/
int xangle=25+180;
/**3D cameran y rotatopm angle
*/
int yangle=15;
/**The 3D cmarea distance from the focal point
*/
int dist=700;//camera presets
/**The sound index map for all of the tutorial narrations
*/
int[][] tutorialNarration=new int[2][17];


//a few more flotas that need to be after some of the ints
/**The 3D camera Y diffrence
*/
float DY=sin(radians(yangle))*dist;
/**Tmp var for computering 3D camera positioing
*/
float hd=cos(radians(yangle))*dist;
/**3D cmarea X diffrence
*/
float DX=sin(radians(xangle))*hd;
/**3D camerea Z diffrence
*/
float DZ=cos(radians(xangle))*hd;
/**3D camrea x position
*/
float cam3Dx;
/**3D camera y position
*/
float cam3Dy;
/**3D camera z position
*/
float cam3Dz;//camera rotation

/**Physics Frame id, incremde through the physics process to prevent entities from being moved twice in the same action
*/
long pfid = 0;

/**The saved level creator colors
*/
JSONArray colors;
/**Completed level progress
*/
JSONArray levelProgress;
/**Unused
*/
JSONArray scolors;

/**Portal creation infomarion for portal 1
*/
JSONObject portalStage1;
/**Portal creation information for portal 2
*/
JSONObject portalStage2;

/**All game settings
*/
Settings settings;

/**Turn shadows off button
*/
Button shadows0;
/**Old shadows button
*/
Button shadows1;
/**Low shadows button
*/
Button shadows2;
/**Medium shadows button
*/
Button shadows3;
/**High shadowns button
*/
Button shadows4;
/**Level 1 button
*/
Button select_lvl_1;
/**Level selection screen back button
*/
Button select_lvl_back;
/**Level 2 button
*/
Button select_lvl_2;
/**Level 3 button
*/
Button select_lvl_3;
/**Level 4 button
*/
Button select_lvl_4;
/**Level 5 button
*/
Button select_lvl_5;
/**Level 6 button
*/
Button select_lvl_6;
/**unused
*/
Button sdSlider;
/**Show FPS button
*/
Button enableFPS;
/**Don't show FPS button
*/
Button disableFPS;
/**Show debug information button
*/
Button enableDebug;
/**Don't show deug information button
*/
Button disableDebug;
/**Gameplay settings screen button
*/
Button sttingsGPL;
/**Display settings screen button
*/
Button settingsDSP;
/**Other settings screen button
*/
Button settingsOUT;
/**720 resolution button
*/
Button rez720;
/**900 resolution button
*/
Button rez900;
/**1080 resolution button
*/
Button rez1080;
/**1440 resolution button
*/
Button rez1440;
/**4K resolution button
*/
Button rez4k;
/**Fullscreen on button
*/
Button fullScreenOn;
/**Fullscreen off button
*/
Button fullScreenOff;
/**unused
*/
Button vsdSlider;
/**unused
*/
Button MusicSlider;
/**unused
*/
Button SFXSlider;
/**Fun narration mode button
*/
Button narrationMode1;
/**Safe narration mode button
*/
Button narrationMode0;
/**Go to UGC menu button
*/
Button select_lvl_UGC;
/**Open UGC folder button
*/
Button UGC_open_folder;
/**Next UGC level button
*/
Button UGC_lvls_next;
/**prevous UGC level button
*/
Button UGC_lvls_prev;
/**Play UGC level button
*/
Button UGC_lvl_play;
/**Open level creator button.
Origonaly this button justed opened the level creatoe web page in a browser but now it open the full level creator
*/
Button levelcreatorLink;
/**Level 7 button
*/
Button select_lvl_7;
/**Level 8 button
*/
Button select_lvl_8;
/**Level 9 button
*/
Button select_lvl_9;
/**Level 10 button
*/
Button select_lvl_10;
/**Main menu play button
*/
Button playButton;
/**Main menu multyplayer button
*/
Button joinButton;
/**Main menu settings button
*/
Button settingsButton;
/**Main menu tutorial button
*/
Button howToPlayButton;
/**Exit game button
*/
Button exitButton;
/**Dowload updater button
*/
Button downloadUpdateButton;
/**Open cbi-games.org website
*/
Button updateGetButton;
/**Close update screen button
*/
Button updateOkButton;
/**Dev menu go to main menu
*/
Button dev_main;
/**Dev menu quit the game
*/
Button dev_quit;
/**Dev menu go to level select screen
*/
Button dev_levels;
/**Dev menu go to turorial
*/
Button dev_tutorial;
/**Dev menu go to settings
*/
Button dev_settings;
/**Dev menu go to UGC levels
*/
Button dev_UGC;
/**Dev menu go to multyplayer
*/
Button dev_multiplayer;
/**Dev menu go to level creator
*/
Button dev_levelCreator;
/**Dev menu go to test level in the level creator
*/
Button dev_testLevel;
/**Go to multyplayer join screen
*/
Button multyplayerJoin;
/**Go to multyplayer host screen
*/
Button multyplayerHost;
/**Exit multyplayer
*/
Button multyplayerExit;
/**Start multyplayer session / connect button
*/
Button multyplayerGo;
/**Leave multyplayer button
*/
Button multyplayerLeave;
/**Multyplayer speedrun levels
*/
Button multyplayerSpeedrun;
/**multyplayer co op levels
*/
Button multyplayerCoop;
/**multyplayer UGC levels
*/
Button multyplayerUGC;
/**Start multyplayer game button
*/
Button multyplayerPlay;
/**Increase speedrun time button
*/
Button increaseTime;
/**Decrease speed run time button
*/
Button decreaseTime;
/**Restart speed run from pause menu button
*/
Button pauseRestart;
/**New level screen button
*/
Button newLevelButton;
/**Load level screen button
*/
Button loadLevelButton;
/**New stage screen button
*/
Button newStage;
/**Create new level file button
*/
Button newFileCreate;
/**Back to overview from new file screen
*/
Button newFileBack;
/**edit sellected stage button
*/
Button edditStage;
/**Set selected stage as main stage button
*/
Button setMainStage;
/**Create portal select destination stage button
*/
Button selectStage;
/**New file will be 2D stage button
*/
Button new2DStage;
/**New file will be 3D stage button
*/
Button new3DStage;
/**Save level in overview button
*/
Button overview_saveLevel;
/**Level creator help button
*/
Button help;
/**New blueprint screen button
*/
Button newBlueprint;
/**Load blueprint screen button
*/
Button loadBlueprint;
/**Create/load the blueprint button
*/
Button createBlueprintGo;
/**New file will be sound button
*/
Button addSound;
/**Scroll up in the overview
*/
Button overviewUp;
/**Scroll down in the overview
*/
Button overviewDown;
/**Select file to copy in add file button
*/
Button chooseFileButton;
/**Load level button
*/
Button lcLoadLevelButton;
/**Create new level button
*/
Button lcNewLevelButton;
/**Go back to UGC from level creator main menu
*/
Button lc_backButton;
/**Exit level creator button
*/
Button lcOverviewExitButton;
/**Confirm exiting the level creator
*/
Button lc_exitConfirm;
/**Cancle exiting the level creator
*/
Button lc_exitCancle;
/**Open the Levels folder for the level creator
*/
Button lc_openLevelsFolder;
/**Back button in the settings menu
*/
Button settingsBackButton;
/**Resume game button from the pause menu
*/
Button pauseResumeButton;
/**Settings button from the pause menu
*/
Button pauseOptionsButton;
/**Quit level button
*/
Button pauseQuitButton;
/**Continue button once the level has been completed
*/
Button endOfLevelButton;
/**Level 11 button
*/
Button select_lvl_11;
/**Level 12 button
*/
Button select_lvl_12;
/**Sound settings screen button
*/
Button settingsSND;
/**Set this new sound to be played as a sound button
*/
Button lc_newSoundAsSoundButton;
/**Set this new sound to be played as a narration button
*/
Button lc_newSoundAsNarrationButton;
/**Diables menu transition animations button
*/
Button disableMenuTransistionsButton;
/**Enable menu transition animarions button
*/
Button enableMenuTransitionButton;
/**Level 13 button
*/
Button select_lvl_13;
/**Level 14 button
*/
Button select_lvl_14;
/**Level 15 button
*/
Button select_lvl_15;
/**Level 16 button
*/
Button select_lvl_16;
/**Level select screen next screen button
*/
Button select_lvl_next;

/**User interface scaling and positioning manager
*/
UiFrame ui;

/**Main menu title
*/
UiText mm_title;
/**Main menu early access text
*/
UiText mm_EarlyAccess;
/**Main menu version text
*/
UiText mm_version;
/**Level select screen title text
*/
UiText ls_levelSelect;
/**UGC menu title text
*/
UiText lsUGC_title;
/**UGC menu no levels found text
*/
UiText lsUGC_noLevelFound;
/**UGC level not comptable text
*/
UiText lsUGC_levelNotCompatible;
/**UGC level name text
*/
UiText lsUGC_levelName;
/**Settings title text
*/
UiText st_title;
/**horozontal scroll resolution text
*/
UiText st_Hssr;
/**vertical scroll resolution text
*/
UiText st_Vssr;
/**Settings gameplay title text
*/
UiText st_gameplay;
/**Verticale scrolling value text
*/
UiText st_vsrp;
/**Horozontal scrolling value text
*/
UiText st_hsrp;
/**Fov setting text
*/
UiText st_gmp_fovdesc;
/**FOV value text
*/
UiText st_gmp_fovdisp;
/**Screen resolution text
*/
UiText st_dsp_vsr;
/**Fullscreen text
*/
UiText st_dsp_fs;
/**4K text
*/
UiText st_dsp_4k;
/**1440 text
*/
UiText st_dsp_1440;
/**1080 text
*/
UiText st_dsp_1080;
/**900 text
*/
UiText st_dsp_900;
/**720 text
*/
UiText st_dsp_720;
/**yes fullscreen text
*/
UiText st_dsp_fsYes;
/**no fullscreen text
*/
UiText st_dsp_fsNo;
/**set FPS value
*/
UiText st_dsp_fpsNum;
/**Frame rate text
*/
UiText st_dsp_fps;
/**Display settings title
*/
UiText st_display;
/**Show FPS text
*/
UiText st_o_displayFPS;
/**Show debug info text
*/
UiText st_o_debugINFO;
/**Music volume text
*/
UiText st_snd_musicVol;
/**Sound volume text
*/
UiText st_snd_SFXvol;
/**Shadow Text
*/
UiText st_o_3DShadow;
/**Narration volume text
*/
UiText st_snd_narration;
/**No column
*/
UiText st_o_yes;
/**Yes colunm
*/
UiText st_o_no;
/**Shadows off
*/
UiText st_o_shadowsOff;
/**Old style shadows
*/
UiText st_o_shadowsOld;
/**Low shaodws
*/
UiText st_o_shadowsLow;
/**Medium shadows
*/
UiText st_o_shadowsMedium;
/**High shadows
*/
UiText st_o_shadowsHigh;
/**Better narrations
*/
UiText st_snd_better;
/**save narrations
*/
UiText st_snd_demonitized;
/**Music volume value display
*/
UiText st_snd_currentMusicVolume;
/**Sound volume value display
*/
UiText st_snd_currentSoundsVolume;
/**Other settings title
*/
UiText st_other;
/**Multyplayer title
*/
UiText initMultyplayerScreenTitle;
/**Host session title
*/
UiText mp_hostSeccion;
/**Host enter name
*/
UiText mp_host_Name;
/**Host enter port
*/
UiText mp_host_port;
/**Join session title
*/
UiText mp_joinSession;
/**Join enter name
*/
UiText mp_join_name;
/**Join enter port
*/
UiText mp_join_port;
/**join enter ip
*/
UiText mp_join_ip;
/**Disconnect screen title
*/
UiText mp_disconnected;
/**Disconnect screen reason
*/
UiText mp_dc_reason;
/**Dev manu title
*/
UiText dev_title;
/**Dev menu info text
*/
UiText dev_info;
/**Tutorial feature disabled text
*/
UiText tut_notToday;
/**Tutorail start disclainer
*/
UiText tut_disclaimer;
/**Tutorial esc to close message
*/
UiText tut_toClose;
/**Number of collected coins display
*/
UiText coinCountText;
/**Paused screen title
*/
UiText pa_title;
/**Startup logo games text
*/
UiText logoText;
/**Update screen title
*/
UiText up_title;
/**Update screen info text
*/
UiText up_info;
/**Update screen wait for download
*/
UiText up_wait;
/**Level creator version display
*/
UiText lc_start_version;
/**Level creator author display
*/
UiText lc_start_author;
/**Enter level name iunstruction
*/
UiText lc_load_new_describe;
/**Level not found text
*/
UiText lc_load_notFound;
/**Show the name of the selected file for copying files into a level
*/
UiText lc_newf_fileName;
/**Portal creation Select destiantion stage text
*/
UiText lc_dp2_info;
/**Enter blueprint name instruction
*/
UiText lc_newbp_describe;
/**Are you sure you want to exit text
*/
UiText lc_exit_question;
/**Any unsaved data Text
*/
UiText lc_exit_disclaimer;
/**You died text
*/
UiText deadText;
/**FPS display
*/
UiText fpsText;
/**mspc display
*/
UiText dbg_mspc;
/**Player x position display
*/
UiText dbg_playerX;
/**Player y position display
*/
UiText dbg_playerY;
/**Player z position display
*/
UiText dbg_playerZ;
/**Player verticale velocity display
*/
UiText dbg_vertvel;
/**Player walking animation cool down
*/
UiText dbg_animationCD;
/**Current Player walking animation pose
*/
UiText dbg_pose;
/**cam x pos display
*/
UiText dbg_camX;
/**cam y pos display
*/
UiText dbg_camY;
/**tutorial position display
*/
UiText dbg_tutorialPos;
/**General display text (Press E)
*/
UiText game_displayText;
/**Level complete text
*/
UiText lebelCompleteText;
/**Level crator main menu fullscreen warning
*/
UiText lc_fullScreenWarning;
/**Level creator text informing the user they are currenly setting the inital player spawn point
*/
UiText settingPlayerSpawnText;
/**Sound setting page title
*/
UiText st_sound;
/**Narration volume descripton
*/
UiText st_snd_narrationVol;
/**Narration volume display
*/
UiText st_snd_currentNarrationVolume;
/**Low narration volume infomration text
*/
UiText narrationCaptionText;
/**Settings disabled menu transitions label
*/
UiText st_o_diableTransitions;
/**Settings default author descripton
*/
UiText st_o_defaultAuthor;
/**Multyplayer ping display
*/
UiText dbg_ping;
/**stage index display
*/
UiText dbg_stageIndex;
/**current stage index display
*/
UiText dbg_currentStageIndex;

/**Music volume slider
*/
UiSlider musicVolumeSlider;
/**Sounds volume slider
*/
UiSlider SFXVolumeSlider;
/**Verticale edge scrolling sitance slider
*/
UiSlider verticleEdgeScrollSlider;
/**Horozontal edge scrolling distance slider
*/
UiSlider horozontalEdgeScrollSlider;
/**Narration volume slider
*/
UiSlider narrationVolumeSlider;
/**FOV Slider
*/
UiSlider fovSlider;
/**Target FPS slider
*/
UiSlider fpsSlider;

/**Default level creator author name setting text box
*/
UiTextBox defaultAuthorNameTextBox;
/**Multyplayer Name text box
*/
UiTextBox multyPlayerNameTextBox;
/**Multyplayer port text box
*/
UiTextBox multyPlayerPortTextBox;
/**Muktyplayer ip text box
*/
UiTextBox multyPlayerIpTextBox;
/**Levle creator enter level name text box
*/
UiTextBox lcEnterLevelTextBox;
/**Level creator new file name text box
*/
UiTextBox lcNewFileTextBox;

/**Server for multyplayer hosting
*/
Server server;


/**Infomration about the currently slected multyyplayer level
*/
SelectedLevelInfo multyplayerSelectedLevel = new SelectedLevelInfo();

/**Leaderboard for multy[player speedrun levels
*/
LeaderBoard leaderBoard = new LeaderBoard(new String[]{"", "", "", "", "", "", "", "", "", ""});

/**The 3D point in front of the camerea that the mouse is over
*/
Point3D initalMousePoint = new Point3D(0, 0, 0);
/**The inital poition of the movemnt object
*/
Point3D initalObjectPos = new Point3D(0, 0, 0);
/**The inital size of the movemnet object
*/
Point3D initialObjectDim = new Point3D(0, 0, 0);
/**Point in 3D space representing the location of the mouse pointer
*/
Point3D mousePoint = new Point3D(0, 0, 0);

/**A list of the boxes that make up the glitch effect
*/
ArrayList<GlitchBox> glitchBoxes = new ArrayList<>();

/**The players movemnt input manager
*/
PlayerMovementManager playerMovementManager = new PlayerMovementManager();

/**Should be unused
*/
CollisionDetection collisionDetection = new CollisionDetection();

/**Player statistics
*/
StatisticManager stats;

/**The sound handler
*/
SoundHandler soundHandler;

/**The currently open level
*/
Level level;

/**The blurprint that is currently being worked on
*/
Stage workingBlueprint;
/**All the loaded blueprints
*/
Stage blueprints[];
/**The blueprint that is curretnly being previewd on the screen
*/
Stage displayBlueprint;

/**The thread responcable for handling in game logig processing
*/
LogicThread logicTickingThread = new LogicThread();

/**The tool box window
*/
ToolBox scr2;

/**The identifier of the component that is currently being placed
*/
Identifier currentlyPlaceing = null;

/**The all registerd proerty config uis
*/
ArrayList<PropertyConfigUi.PropConfigUiFactory> propertyConfigRegistry = new ArrayList<>();

/**Connected multyplayer clients
*/
ArrayList<Client> clients= new ArrayList<>();

/**A proper non this refrence to the main Papplet (for refrence from the tool box window)
*/
PApplet primaryWindow=this;

/**The current state of all players
*/
Player players[] =new Player[10];


//DO NOT EDIT BELOW THIS LINE ON THE MAIN PROJECT!
//===================================================
//DO NOT EDIT THEESE LINES, EVER
//+++++++++++++++++++++++++++++++++++++++++++++++++++
//===================================================
//reserved for arcade edition vars



//===================================================
//DO NOT EDIT THEESE LINES, EVER
//+++++++++++++++++++++++++++++++++++++++++++++++++++
//===================================================
//reserverd for other external var decaliresion



//end of vars.pde
