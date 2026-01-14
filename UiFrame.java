import processing.core.*;
/**A positional frame to manage positioning and scaling of user interfaces so they are allways centerd with an unchanging aspsct ration that will allways fit to the window
*/
public class UiFrame {
  private PGraphics render;
  private int baseWidth, baseHeight;
  private float topX, topY, centerX, centerY, scale;
  
  /**Create a new ui frame with a base screen size
  @param render The surface to scale to
  @param baseWidth The base pixel width to scale widths from
  @param baseHeight The base pixel height to scale heights from
  */
  public UiFrame(PGraphics render, int baseWidth, int baseHeight) {
    this.render=render;
    this.baseWidth=baseWidth;
    this.baseHeight=baseHeight;
    centerX=render.width/2;
    centerY=render.height/2;
    scale=PApplet.min((float)render.width/baseWidth, (float)render.height/baseHeight);//calculate the UI scale
    topX=centerX-baseWidth*scale/2;
    topY=centerY-baseHeight*scale/2;
  }
  
  /**Recalculate the scale after a change in the parent window size
  */
  public void reScale() {
    centerX=render.width/2;
    centerY=render.height/2;
    scale=PApplet.min((float)render.width/baseWidth, (float)render.height/baseHeight);
    topX=centerX-baseWidth*scale/2;
    topY=centerY-baseHeight*scale/2;
  }
  /**Get the top (left) x coordinate of the Ui frame (may not be 0)
  @return The x value of where the top left of the Ui should be
  */
  public float topX() {
    return topX;
  }
  /**Get the top y coordinate of the Ui frame (may not be 0)
  @return The y value of where the top left of the Ui should be
  */
  public float topY() {
    return topY;
  }
  /**Get the center x coordinate of the Ui frame
  @return The x value of the center of the screen
  */
  public float centerX() {
    return centerX;
  }
  /**Get the center y coordinate of the Ui frame
  @return The y value of the center of the screen
  */
  public float centerY() {
    return centerY;
  }
  /**Get the Ui scale of this ui frame
  @return The scale of this frame
  */
  public float scale() {
    return scale;
  }
  /**Get the surface to render to
  @return the surface to rener to
  */
  public PGraphics getRender() {
    return render;
  }
}
