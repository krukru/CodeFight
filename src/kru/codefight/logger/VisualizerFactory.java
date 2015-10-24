package kru.codefight.logger;

public class VisualizerFactory {
  public static AbstractFightVisualizer createVisualizer() {
    //currently the best one
    return new ConsoleVisualizer();
  }
}
