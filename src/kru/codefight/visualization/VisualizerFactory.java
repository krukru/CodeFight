package kru.codefight.visualization;

public class VisualizerFactory {
  public static AbstractFighterVisualizer createVisualizer() {
    //currently the best one
    return new ConsoleVisualizer();
  }
}
