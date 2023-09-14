class UniversalErrorManager{
  public static void init(skiny_mann source){
    Thread.setDefaultUncaughtExceptionHandler( new Thread.UncaughtExceptionHandler() {
      public void uncaughtException(Thread t, Throwable e)
          {
            System.err.println("UniversalErrorManager has cought an error in thread: "+t.toString());
            source.handleError(e);
          }
      });
  }
}
