package kru.codefight.visualizer;

public class VisualizerFactory {
  public static AbstractFightVisualizer createVisualizer() {
    //currently the best one
    return new ConsoleVisualizer();
  }
}
