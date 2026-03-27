import { WebPlugin } from '@capacitor/core';

import type { capacitormapboxnavPlugin } from './definitions';

export class capacitormapboxnavWeb extends WebPlugin implements capacitormapboxnavPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
