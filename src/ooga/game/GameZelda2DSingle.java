package ooga.game;

import java.io.IOException;
import ooga.view.game_view.game_state.state2d.GameState2DView;

public class GameZelda2DSingle implements Runnable {

  private Thread game;
  private GameState2DView view;
  private boolean isUpdating = false;
  private int id;
  private String direction;
  private String state;

  public void start() {
    game = new Thread(this, "game");
    game.start();
  }

  public void init() throws IOException {
    view = new GameState2DView(1);
    view.createWindow();
  }

  public void run() {
    try {
      init();
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (!view.shouldWindowClose()) {
      update();
      render();
    }
    close();
  }

  private void update() {
    view.updateWindow();
    view.updateMap(); //empty method
    view.renderNPCs(); // empty method
    if (isUpdating){
      view.updatePlayer(id,direction,state);
      isUpdating = false;
    }

  }

  public void updatePlayer(int id, String direction, String state) {
    isUpdating = true;
    this.id = id;
    this.direction = direction;
    this.state = state;
  }

  private void render() {
    view.renderAll();
    // can also render separately, renderWindow() is mandatory
  }

  private void close() {
    view.closeWindow();
  }

  public GameState2DView getView() {
    return view;
  }
}
