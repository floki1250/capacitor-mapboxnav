import { capacitormapboxnavWeb } from './web';

describe('capacitormapboxnavWeb', () => {
    let plugin: capacitormapboxnavWeb;

    beforeEach(() => {
        plugin = new capacitormapboxnavWeb();
    });

    test('echo should return the provided value', async () => {
        const input = { value: 'test' };
        const result = await plugin.echo(input);
        expect(result.value).toBe(input.value);
    });

    test('initialize should complete without error', async () => {
        const options = { accessToken: 'MAPBOX_DOWNLOADS_TOKEN' };
        await expect(plugin.initialize(options)).resolves.toBeUndefined();
    });

    test('startNavigation should complete without error', async () => {
        const options = {
            origin: { latitude: 0, longitude: 0 },
            destination: { latitude: 1, longitude: 1 },
            simulateRoute: true
        };
        await expect(plugin.startNavigation(options)).resolves.toBeUndefined();
    });
});
