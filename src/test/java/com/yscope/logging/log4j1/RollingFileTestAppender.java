package com.yscope.logging.log4j1;

/**
 * A rolling file appender used for testing
 * {@link AbstractClpIrBufferedRollingFileAppender}. It specifically allows us
 * to control the time visible to the appender and tracks the number of syncs
 * and rollover events.
 */
public class RollingFileTestAppender extends AbstractClpIrBufferedRollingFileAppender {
  private int numSyncs = 0;
  private int numRollovers = 0;

  public RollingFileTestAppender () {
    super(new ManualTimeSource());
  }

  /**
   * Sets the current time visible to the appender
   * @param timestamp The current time
   */
  public void setTime (long timestamp) {
    timeSource.setCurrentTimeInMilliseconds(timestamp);
  }

  public synchronized int getNumRollovers () {
    return numRollovers;
  }

  public synchronized int getNumSyncs () {
    return numSyncs;
  }

  /**
   * Tracks the number of syncs and rollovers
   * @param path {@inheritDoc}
   * @param deleteFile {@inheritDoc}
   */
  @Override
  protected synchronized void sync (String path, boolean deleteFile) {
    if (deleteFile) {
      numRollovers += 1;
    } else {
      numSyncs += 1;
    }
  }
}
