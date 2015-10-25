package kru.codefight.visualizer;

import kru.codefight.visualizer.label.SimpleLabelVisualizer;

public class VisualizerFactory {
  public static AbstractFightVisualizer createVisualizer() {
    //currently the best one
    return new SimpleLabelVisualizer();
  }
}
