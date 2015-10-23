package kru.codefight.logger;

public class LoggerFactory {
  public static AbstractFightLogger createLogger() {
    //currently the best one
    return new DetailedLogger();
  }
}
