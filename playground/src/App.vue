<script setup lang="ts">
import { ref } from 'vue';
import { capacitormapboxnav } from '@castelio-it/capacitor-mapboxnav';
import { Geolocation } from '@capacitor/geolocation';

const isSimulating = ref(true);

async function requestPermissions() {
  try {
    const status = await Geolocation.requestPermissions();
    if (status.location !== 'granted') {
      alert('Location permissions are required for this plugin.');
    }
  } catch (e) {
    console.error('Permission error', e);
  }
}

async function initPlugin() {
  await capacitormapboxnav.initialize({
    accessToken: import.meta.env.VITE_MAPBOX_DOWNLOAD_TOKEN
  });
}

// 1. Test Simple Navigation (No guidance UI)
async function startSimpleNavigation() {
  await initPlugin();
  await capacitormapboxnav.startNavigation({
    origin: { latitude: 35.723976, longitude: 10.745365 },
    destination: { latitude: 35.831016, longitude: 10.626204 },
    simulateRoute: isSimulating.value
  });
}

// 2. Test Turn-by-Turn (Full Guidance UI with maneuvers/voice)
async function startTurnByTurn() {
  await initPlugin();
  await capacitormapboxnav.startTurnByTurnExperience({
    origin: { latitude: 35.723976, longitude: 10.745365 },
    destination: { latitude: 35.831016, longitude: 10.626204 },
    simulateRoute: isSimulating.value
  });
}

// 3. Test Location Tracking (Free Drive / No destination)
async function startFreeDrive() {
  await initPlugin();
  await capacitormapboxnav.startFreeDrive();
}
</script>

<template>
  <main>
    <header class="header">
      <h1>Mapbox Nav Test</h1>
    </header>

    <section class="controls">
      <!-- Simulation Toggle -->
      <div class="toggle-container">
        <label>
          <input type="checkbox" v-model="isSimulating" />
          Simulate Movement
        </label>
      </div>

      <div class="button-group">
        <button @click="requestPermissions" class="btn-secondary">Request Permissions</button>

        <hr />

        <button @click="startFreeDrive" class="btn-primary">
          📡 Start Location Tracking (Free Drive)
        </button>

        <button @click="startSimpleNavigation" class="btn-primary">
          🗺️ Start Simple Navigation
        </button>

        <button @click="startTurnByTurn" class="btn-primary accent">
          🏎️ Start Full Turn-by-Turn
        </button>
      </div>
    </section>
  </main>
</template>

<style scoped>
main {
  font-family: system-ui, -apple-system, sans-serif;
  padding: 20px;
  max-width: 400px;
  margin: 0 auto;
}

.header {
  text-align: center;
  margin-bottom: 30px;
}

.toggle-container {
  margin-bottom: 20px;
  padding: 10px;
  background: #f0f0f0;
  border-radius: 8px;
}

.button-group {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

button {
  padding: 15px;
  border: none;
  border-radius: 8px;
  font-weight: bold;
  cursor: pointer;
  font-size: 16px;
  transition: opacity 0.2s;
}

.btn-primary {
  background-color: #007aff;
  color: white;
}

.btn-secondary {
  background-color: #8e8e93;
  color: white;
}

.accent {
  background-color: #34c759;
}

button:active {
  opacity: 0.7;
}

hr {
  border: 0;
  border-top: 1px solid #ddd;
  margin: 10px 0;
}
</style>
