import { registerPlugin } from '@capacitor/core';

import type { capacitormapboxnavPlugin } from './definitions';

const capacitormapboxnav = registerPlugin<capacitormapboxnavPlugin>('capacitormapboxnav', {
  web: () => import('./web').then((m) => new m.capacitormapboxnavWeb()),
});

export * from './definitions';
export { capacitormapboxnav };
