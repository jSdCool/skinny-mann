PShader shadowShader;
PShader depthBufferShader;
PGraphics shadowMap, cameraMatrixMap;
PGraphics[] subShadowMaps = new PGraphics[4];
PImage shadowMapCopy = createImage(100,100,ARGB);
PImage uvTester;
boolean requestDepthBufferInit = false ;
boolean showDepthBuffer = false;
boolean shadowShaderOutputSampledDepthInfo = false;
PVector lightDir = new PVector();
Settings settings;

Button shadows0, shadows1, shadows2, shadows3, shadows4;
UiText st_o_shadowsOff, st_o_shadowsOld, st_o_shadowsLow, st_o_shadowsMedium, st_o_shadowsHigh;
UiText loadingText;
