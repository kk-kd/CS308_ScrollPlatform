package ooga.view.engine.utils.cyberpunk3d;

import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.utils.FileUtils;

public class Text3DMapReader {

  private TitleDataHolder[] tiles;
  private String separator = ",";

  private int tileAmounts;

  public Text3DMapReader(String path){
    String mapText = FileUtils.loadAsString(path, "");
    String[] mapContent = mapText.split(separator);

    int idx = 0;

    tileAmounts = Integer.parseInt(mapContent[idx++]);
    tiles = new TitleDataHolder[tileAmounts];

    for (int i=0; i<tileAmounts; i++){
      int id = Integer.parseInt(mapContent[idx++]);
      String type = mapContent[idx++];
      int rotation = Integer.parseInt(mapContent[idx++]);
      boolean newLine = (Integer.parseInt(mapContent[idx++]) != 0);
      tiles[i] = new TitleDataHolder(id, type, rotation, newLine);
    }
  }

  public int getTileAmounts(){
    return tileAmounts;
  }

  public String getTileType(int index){
    return tiles[index].getType();
  }

  public Vector3f getTileRotation(int index){
    return tiles[index].getRotation();
  }

  public boolean isTiLeNewline(int index){
    return tiles[index].isNewLine();
  }


}
