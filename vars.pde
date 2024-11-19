PShader shadowShader;
PShader depthBufferShader;
PGraphics shadowMap;
PImage shadowMapCopy = createImage(100,100,ARGB);
boolean requestDepthBufferInit = false ;
boolean showDepthBuffer = false;
PVector lightDir = new PVector();
Settings settings;
