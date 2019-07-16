package com.maxwellhaley.war.core.exception;

import com.maxwellhaley.war.core.model.Player;

/**
 * An exception that is raised whenever a user makes a {@link Player#bet(int)}
 * that is greater than the same users {@link Player#cash}.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-14
 */
public class NotEnoughCashException extends Exception {

  /** Generated serial version UID */
  private static final long serialVersionUID = 8173365003397522456L;

  public NotEnoughCashException() {}

}
