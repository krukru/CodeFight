package kru.codefight.visualization;

public class Visualizer {

  private static AbstractFightVisualizer instance;

  public static AbstractFightVisualizer instance() {
    if (instance == null) {
      instance = VisualizerFactory.createVisualizer();
    }
    return instance;
  }

  private Visualizer() {
  }
}
