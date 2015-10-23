package kru.codefight.visualization;

public class Visualizer {

  private static AbstractFighterVisualizer instance;

  public static AbstractFighterVisualizer instance() {
    if (instance == null) {
      instance = VisualizerFactory.createVisualizer();
    }
    return instance;
  }

  private Visualizer() {
  }
}
