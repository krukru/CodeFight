package kru.codefight.logger;

public class LoggerFactory {
  public static AbstractFightLogger createVisualizer() {
    //currently the best one
    return new SimpleLogger();
  }
}
