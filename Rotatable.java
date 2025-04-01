import processing.core.PVector;
interface Rotatable{
  
  void resetRotate();
  void rotateX(float x);
  void rotateY(float y);
  void rotateZ(float z);
  void updateVerticies();
  float getRotateX();
  float getRotateY();
  float getRotateZ();
  PVector getXLocal();
  PVector getYLocal();
  PVector getZLocal();
}
