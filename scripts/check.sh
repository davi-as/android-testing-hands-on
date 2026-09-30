#!/bin/bash

echo "=== Jornada Android: Setup Checker ==="
echo ""

ERRORS=0

# 1. JDK 17
echo "✓ Checking JDK 17..."
if java -version 2>&1 | grep -q "17"; then
    echo "  ✓ JDK 17 found"
else
    echo "  ✘ JDK 17 not found or wrong version"
    ERRORS=$((ERRORS + 1))
fi

# 2. adb devices
echo "✓ Checking Android Debug Bridge..."
if adb devices | grep -q "device$"; then
    echo "  ✓ Emulator/device connected"
else
    echo "  ✘ No emulator/device online. Start emulator first."
    ERRORS=$((ERRORS + 1))
fi

# 3. Appium
echo "✓ Checking Appium..."
if command -v appium &> /dev/null; then
    echo "  ✓ Appium CLI installed"
else
    echo "  ✘ Appium CLI not found. Run: npm i -g appium"
    ERRORS=$((ERRORS + 1))
fi

# 4. Appium UiAutomator2 driver
echo "✓ Checking Appium UiAutomator2 driver..."
if [ -d "$HOME/.appium/node_modules/appium-uiautomator2-driver" ]; then
    echo "  ✓ UiAutomator2 driver installed"
else
    echo "  ✘ UiAutomator2 driver not found. Run: appium driver install uiautomator2"
    ERRORS=$((ERRORS + 1))
fi

# 5. Gradle cache
echo "✓ Checking Gradle cache..."
if [ -d "$HOME/.gradle/caches" ]; then
    echo "  ✓ Gradle cache found"
else
    echo "  ⚠ Gradle cache not warmed. First build will be slow."
fi

# 6. AnkiDroid APK
echo "✓ Checking AnkiDroid APK..."
if [ -f "AnkiDroid.apk" ] || [ -n "$ANKIDROID_APK" ]; then
    echo "  ✓ AnkiDroid APK found"
else
    echo "  ✘ AnkiDroid APK not found. Download from: https://github.com/ankidroid/Anki-Android/releases"
    ERRORS=$((ERRORS + 1))
fi

echo ""
echo "=== Summary ==="
if [ $ERRORS -eq 0 ]; then
    echo "✓ All checks passed! Ready to go."
    exit 0
else
    echo "✘ $ERRORS check(s) failed. Fix above and retry."
    exit 1
fi
