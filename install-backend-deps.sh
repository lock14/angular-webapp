#!/bin/bash

# Exit on error
set -e

OS="$(uname -s)"

echo "--- STEP 1: Checking environment ---"
if [ "$OS" = "Darwin" ]; then
    echo "[INFO] Detected macOS."
    if [ "$EUID" -eq 0 ]; then
        echo "[WAIT] Please do NOT run this script as root/sudo on macOS (Homebrew restricts root access)."
        exit 1
    fi
else
    if [ "$EUID" -ne 0 ]; then
        echo "[WAIT] Please run this script as root or using sudo:"
        echo "sudo $0"
        exit 1
    fi
fi

echo "--- STEP 2: Installing JDK 17 and Maven ---"
if [ "$OS" = "Darwin" ]; then
    if command -v brew &> /dev/null; then
        echo "[INFO] Installing openjdk@17 and maven via Homebrew..."
        brew update
        brew install openjdk@17 maven
    else
        echo "[WARNING] Homebrew is not installed. Please install it from https://brew.sh/ and try again."
        exit 1
    fi
elif command -v apt-get &> /dev/null; then
    echo "[INFO] Detected Debian/Ubuntu based system. Installing openjdk-17-jdk and maven..."
    apt-get update -y
    apt-get install -y openjdk-17-jdk maven
elif command -v dnf &> /dev/null; then
    echo "[INFO] Detected Fedora/RHEL based system. Installing java-17-openjdk-devel and maven..."
    dnf install -y java-17-openjdk-devel maven
elif command -v yum &> /dev/null; then
    echo "[INFO] Detected older RHEL/CentOS based system. Installing java-17-openjdk-devel and maven..."
    yum install -y java-17-openjdk-devel maven
else
    echo "[WARNING] Could not find a supported package manager (brew/apt/dnf/yum). Please install JDK 17 and Maven manually."
    exit 1
fi

echo "--- STEP 3: Verification ---"
if command -v mvn &> /dev/null && command -v java &> /dev/null; then
    echo "[SUCCESS] Java and Maven are installed."
    echo ""
    java -version
    echo ""
    mvn -version
else
    echo "[FAIL] Installation did not complete successfully."
    exit 1
fi
