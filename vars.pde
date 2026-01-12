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

/**The coin model
*/
PShape coin3D;
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
/*If connecting logic components
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
/**The file path to the current level / blueprint
*/
String rootPath;
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
/**z position to move the player to
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
/**The sound index map for all of the tutorial sounds
*/
int[][] tutorialNarration=new int[2][17];

JSONArray colors;
JSONArray levelProgress;
JSONArray scolors;

JSONObject portalStage1;
JSONObject portalStage2;

Settings settings;

Button shadows0;
Button shadows1;
Button shadows2;
Button shadows3;
Button shadows4;
Button select_lvl_1;
Button select_lvl_back;
Button select_lvl_2;
Button select_lvl_3;
Button select_lvl_4;
Button select_lvl_5;
Button select_lvl_6;
Button sdSlider;
Button enableFPS;
Button disableFPS;
Button enableDebug;
Button disableDebug;
Button sttingsGPL;
Button settingsDSP;
Button settingsOUT;
Button rez720;
Button rez900;
Button rez1080;
Button rez1440;
Button rez4k;
Button fullScreenOn;
Button fullScreenOff;
Button vsdSlider;
Button MusicSlider;
Button SFXSlider;
Button narrationMode1;
Button narrationMode0;
Button select_lvl_UGC;
Button UGC_open_folder;
Button UGC_lvls_next;
Button UGC_lvls_prev;
Button UGC_lvl_play;
Button levelcreatorLink;
Button select_lvl_7;
Button select_lvl_8;
Button select_lvl_9;
Button select_lvl_10;
Button playButton;
Button joinButton;
Button settingsButton;
Button howToPlayButton;
Button exitButton;
Button downloadUpdateButton;
Button updateGetButton;
Button updateOkButton;
Button dev_main;
Button dev_quit;
Button dev_levels;
Button dev_tutorial;
Button dev_settings;
Button dev_UGC;
Button dev_multiplayer;
Button dev_levelCreator;
Button dev_testLevel;
Button multyplayerJoin;
Button multyplayerHost;
Button multyplayerExit;
Button multyplayerGo;
Button multyplayerLeave;
Button multyplayerSpeedrun;
Button multyplayerCoop;
Button multyplayerUGC;
Button multyplayerPlay;
Button increaseTime;
Button decreaseTime;
Button pauseRestart;
Button newLevelButton;
Button loadLevelButton;
Button newStage;
Button newFileCreate;
Button newFileBack;
Button edditStage;
Button setMainStage;
Button selectStage;
Button new2DStage;
Button new3DStage;
Button overview_saveLevel;
Button help;
Button newBlueprint;
Button loadBlueprint;
Button createBlueprintGo;
Button addSound;
Button overviewUp;
Button overviewDown;
Button chooseFileButton;
Button lcLoadLevelButton;
Button lcNewLevelButton;
Button lc_backButton;
Button lcOverviewExitButton;
Button lc_exitConfirm;
Button lc_exitCancle;
Button lc_openLevelsFolder;
Button settingsBackButton;
Button pauseResumeButton;
Button pauseOptionsButton;
Button pauseQuitButton;
Button endOfLevelButton;
Button select_lvl_11;
Button select_lvl_12;
Button settingsSND;
Button lc_newSoundAsSoundButton;
Button lc_newSoundAsNarrationButton;
Button disableMenuTransistionsButton;
Button enableMenuTransitionButton;
Button select_lvl_13;
Button select_lvl_14;
Button select_lvl_15;
Button select_lvl_16;
Button select_lvl_next;

UiFrame ui;

UiText mm_title;
UiText mm_EarlyAccess;
UiText mm_version;
UiText ls_levelSelect;
UiText lsUGC_title;
UiText lsUGC_noLevelFound;
UiText lsUGC_levelNotCompatible;
UiText lsUGC_levelName;
UiText st_title;
UiText st_Hssr;
UiText st_Vssr;
UiText st_gameplay;
UiText st_vsrp;
UiText st_hsrp;
UiText st_gmp_fovdesc;
UiText st_gmp_fovdisp;
UiText st_dsp_vsr;
UiText st_dsp_fs;
UiText st_dsp_4k;
UiText st_dsp_1440;
UiText st_dsp_1080;
UiText st_dsp_900;
UiText st_dsp_720;
UiText st_dsp_fsYes;
UiText st_dsp_fsNo;
UiText st_dsp_fpsNum;
UiText st_dsp_fps;
UiText st_display;
UiText st_o_displayFPS;
UiText st_o_debugINFO;
UiText st_snd_musicVol;
UiText st_snd_SFXvol;
UiText st_o_3DShadow;
UiText st_snd_narration;
UiText st_o_yes;
UiText st_o_no;
UiText st_o_shadowsOff;
UiText st_o_shadowsOld;
UiText st_o_shadowsLow;
UiText st_o_shadowsMedium;
UiText st_o_shadowsHigh;
UiText st_snd_better;
UiText st_snd_demonitized;
UiText st_snd_currentMusicVolume;
UiText st_snd_currentSoundsVolume;
UiText st_other;
UiText initMultyplayerScreenTitle;
UiText mp_hostSeccion;
UiText mp_host_Name;
UiText mp_host_port;
UiText mp_joinSession;
UiText mp_join_name;
UiText mp_join_port;
UiText mp_join_ip;
UiText mp_disconnected;
UiText mp_dc_reason;
UiText dev_title;
UiText dev_info;
UiText tut_notToday;
UiText tut_disclaimer;
UiText tut_toClose;
UiText coinCountText;
UiText pa_title;
UiText logoText;
UiText up_title;
UiText up_info;
UiText up_wait;
UiText lc_start_version;
UiText lc_start_author;
UiText lc_load_new_describe;
UiText lc_load_notFound;
UiText lc_newf_fileName;
UiText lc_dp2_info;
UiText lc_newbp_describe;
UiText lc_exit_question;
UiText lc_exit_disclaimer;
UiText deadText;
UiText fpsText;
UiText dbg_mspc;
UiText dbg_playerX;
UiText dbg_playerY;
UiText dbg_vertvel;
UiText dbg_animationCD;
UiText dbg_pose;
UiText dbg_camX;
UiText dbg_camY;
UiText dbg_tutorialPos;
UiText game_displayText;
UiText lebelCompleteText;
UiText lc_fullScreenWarning;
UiText settingPlayerSpawnText;
UiText st_sound;
UiText st_snd_narrationVol;
UiText st_snd_currentNarrationVolume;
UiText narrationCaptionText;
UiText st_o_diableTransitions;
UiText st_o_defaultAuthor;
UiText dbg_ping;

UiSlider musicVolumeSlider;
UiSlider SFXVolumeSlider;
UiSlider verticleEdgeScrollSlider;
UiSlider horozontalEdgeScrollSlider;
UiSlider narrationVolumeSlider;
UiSlider fovSlider;
UiSlider fpsSlider;

UiTextBox defaultAuthorNameTextBox;
UiTextBox multyPlayerNameTextBox;
UiTextBox multyPlayerPortTextBox;
UiTextBox multyPlayerIpTextBox;
UiTextBox lcEnterLevelTextBox;
UiTextBox lcNewFileTextBox;

Server server;

SelectedLevelInfo multyplayerSelectedLevel = new SelectedLevelInfo();

LeaderBoard leaderBoard = new LeaderBoard(new String[]{"", "", "", "", "", "", "", "", "", ""});

Point3D initalMousePoint = new Point3D(0, 0, 0);
Point3D initalObjectPos = new Point3D(0, 0, 0);
Point3D initialObjectDim = new Point3D(0, 0, 0);

ArrayList<GlitchBox> glitchBoxes = new ArrayList<>();

PlayerMovementManager playerMovementManager = new PlayerMovementManager();

CollisionDetection collisionDetection = new CollisionDetection();

StatisticManager stats;

SoundHandler soundHandler;

Level level;

Stage workingBlueprint;
Stage blueprints[];
Stage displayBlueprint;

LogicThread logicTickingThread = new LogicThread();

ToolBox scr2;

Identifier currentlyPlaceing = null;

ArrayList<PropertyConfigUi.PropConfigUiFactory> propertyConfigRegistry = new ArrayList<>();


ArrayList<Client> clients= new ArrayList<>();

PApplet primaryWindow=this;

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
