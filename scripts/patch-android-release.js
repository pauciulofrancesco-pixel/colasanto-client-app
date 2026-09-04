const fs = require('fs');
const p = 'android/app/build.gradle';
let s = fs.readFileSync(p, 'utf8');
const versionCode = process.env.COLASANTO_VERSION_CODE || '1001';
const versionName = process.env.COLASANTO_VERSION_NAME || '1.0.0';
if (!/defaultConfig\s*\{/.test(s)) throw new Error('defaultConfig non trovato');
s = s.replace(/versionCode\s+\d+/, `versionCode ${versionCode}`);
s = s.replace(/versionName\s+"[^"]+"/, `versionName "${versionName}"`);
if (!s.includes('signingConfigs {')) {
  s = s.replace(/\n\s*defaultConfig\s*\{/, `\n    signingConfigs {\n        release {\n            storeFile file(System.getenv("COLASANTO_KEYSTORE_FILE"))\n            storePassword System.getenv("ANDROID_KEYSTORE_PASSWORD")\n            keyAlias "colasanto"\n            keyPassword System.getenv("ANDROID_KEYSTORE_PASSWORD")\n        }\n    }\n    defaultConfig {`);
}
s = s.replace(/release\s*\{\s*\n\s*minifyEnabled/, `release {\n            signingConfig signingConfigs.release\n            minifyEnabled`);
fs.writeFileSync(p, s);
console.log(`Android release: versionCode=${versionCode}, versionName=${versionName}`);
