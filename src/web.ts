import { WebPlugin } from '@capacitor/core';

import type { capacitormapboxnavPlugin } from './definitions';

export class capacitormapboxnavWeb extends WebPlugin implements capacitormapboxnavPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async initialize(options: { accessToken: string }): Promise<void> {
    console.log('INITIALIZE', options);
  }

  async startNavigation(options: {
    origin: { latitude: number, longitude: number };
    destination: { latitude: number, longitude: number };
    simulateRoute?: boolean;
  }): Promise<void> {
    console.log('START_NAVIGATION', options);
  }
}
