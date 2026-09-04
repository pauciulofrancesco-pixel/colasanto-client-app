import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'it.colasanto.personalstudio',
  appName: 'COLASANTO',
  webDir: 'www',
  android: {
    allowMixedContent: false
  },
  ios: {
    contentInset: 'automatic'
  },
  server: {
    cleartext: false,
    allowNavigation: ['owczbpqzduwkbmcfxttv.supabase.co']
  }
};

export default config;
