package ooga.view.engine.graphics.animation;

import ooga.view.engine.graphics.Material;

public class Animation2D extends Animation{

  private Material[] animatedFrames;
  private boolean is2D = true;

  public Animation2D(int cnt, int fps, String dir) {
    super(cnt,fps);
    this.animatedFrames = new Material[cnt];

    for(int i=0; i<cnt;i++){
      String spritePath = String.format("%s/%s_.png", dir.replace("\\", "/"), i);
      this.animatedFrames[i] = new Material(spritePath);
      this.animatedFrames[i].createTexture();
    }
  }

  public Animation2D(int cnt, int fps) {
    super(cnt, fps);
    this.animatedFrames = new Material[cnt];
  }

  public static Animation2D combineAnimations(Animation2D animation_1, Animation2D animation_2){

    int totalFrames = animation_1.getFrameAmount()+animation_2.getFrameAmount();
    Animation2D combined = new Animation2D(totalFrames, DEFAULT_FPS_2D);

    int idx = 0;
    for (int i=0; i<animation_1.getFrameAmount(); i++){
      combined.setAnimatedFrames(idx++, animation_1.getAnimatedFrames(i));
    }

    for (int i=0; i<animation_2.getFrameAmount(); i++){
      combined.setAnimatedFrames(idx++, animation_2.getAnimatedFrames(i));
    }

    return combined;
  }

  public void setAnimatedFrames(int idx, Material frame){
    this.animatedFrames[idx] = frame;
  }

  public Material getAnimatedFrames(int idx){
    return this.animatedFrames[idx];
  }

  public Material getCurrentFrame(){
    return animatedFrames[updateFramePointer(is2D)];
  }



}