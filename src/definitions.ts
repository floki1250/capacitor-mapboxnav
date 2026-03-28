export interface capacitormapboxnavPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  initialize(options: { accessToken: string }): Promise<void>;
  startNavigation(options: {
    origin: { latitude: number, longitude: number };
    destination: { latitude: number, longitude: number };
    simulateRoute?: boolean;
  }): Promise<void>;
}
