export interface capacitormapboxnavPlugin {
  /**
   * Echo back the provided value.
   */
  echo(options: { value: string }): Promise<{ value: string }>;

  /**
   * Initialize the Mapbox Navigation SDK with your public access token.
   * This must be called before any navigation activity.
   */
  initialize(options: { accessToken: string }): Promise<void>;

  /**
   * Start a basic navigation session with a simple map overview.
   * Useful for background navigation or lightweight tracking.
   */
  startNavigation(options: {
    /** The starting coordinates. */
    origin: { latitude: number; longitude: number };
    /** The target destination coordinates. */
    destination: { latitude: number; longitude: number };
    /** Whether to simulate the movement along the route (true for testing). */
    simulateRoute?: boolean;
  }): Promise<void>;

  /**
   * Start a full Turn-by-Turn navigation experience.
   * Includes instruction banners, maneuvers, speed limits, and total trip progress.
   */
  startTurnByTurnExperience(options: {
    /** The starting coordinates. */
    origin: { latitude: number; longitude: number };
    /** The target destination coordinates. */
    destination: { latitude: number; longitude: number };
    /** Whether to simulate the movement along the route (true for testing). */
    simulateRoute?: boolean;
  }): Promise<void>;

  /**
   * Start a Free Drive session (Position tracking).
   * Shows the map centered on the user with no active route or destination.
   */
  startFreeDrive(): Promise<void>;
}

