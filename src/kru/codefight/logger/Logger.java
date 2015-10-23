package kru.codefight.logger;

public class Logger {

  private static AbstractFightLogger instance;

  public static AbstractFightLogger instance() {
    if (instance == null) {
      instance = LoggerFactory.createVisualizer();
    }
    return instance;
  }

  private Logger() {
  }
}
