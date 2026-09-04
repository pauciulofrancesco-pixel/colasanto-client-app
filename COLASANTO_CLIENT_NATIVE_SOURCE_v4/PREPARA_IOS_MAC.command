#!/bin/bash
set -e
cd "$(dirname "$0")"
npm install --no-audit --no-fund
[ -d ios ] || npx cap add ios
npx cap sync ios
npx capacitor-assets generate --ios
npx cap open ios
