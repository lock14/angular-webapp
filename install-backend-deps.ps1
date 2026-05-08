# DIRECTIVE: THIS SCRIPT MUST BE IDEMPOTENT.
# Fixed version: ASCII only to prevent PowerShell encoding/BOM parser crashes.

# 0. SELF-ELEVATION
$currentPrincipal = New-Object Security.Principal.WindowsPrincipal([Security.Principal.WindowsIdentity]::GetCurrent())
if (-not $currentPrincipal.IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)) {
    Write-Host "[WAIT] Elevating... Please check the NEW blue Administrator window." -ForegroundColor Yellow
    Start-Process powershell.exe -ArgumentList "-NoExit -NoProfile -ExecutionPolicy Bypass -File `"$PSCommandPath`"" -Verb RunAs
    exit
}

# 1. CONFIGURATION
$JDK_ID = "Microsoft.OpenJDK.17"
$MAVEN_VERSION = "3.9.15"
$MAVEN_ROOT = "C:\Maven"
$MAVEN_HOME = Join-Path $MAVEN_ROOT "apache-maven-$MAVEN_VERSION"
$MAVEN_BIN = Join-Path $MAVEN_HOME "bin"

# 2. INSTALL JDK
Write-Host "`n--- STEP 1: JDK Check ---" -ForegroundColor Magenta
winget install --id $JDK_ID --silent --accept-package-agreements --accept-source-agreements

# 3. INSTALL MAVEN
Write-Host "`n--- STEP 2: Maven Check ---" -ForegroundColor Magenta
$mvnCmd = Join-Path $MAVEN_BIN "mvn.cmd"

if (!(Test-Path $mvnCmd)) {
    Write-Host "[INFO] Maven binaries missing. Installing..." -ForegroundColor Cyan
    if (!(Test-Path $MAVEN_ROOT)) { New-Item -Path $MAVEN_ROOT -ItemType Directory -Force | Out-Null }
    
    $zip = "$env:TEMP\mvn.zip"
    $permUrl = "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$MAVEN_VERSION/apache-maven-$MAVEN_VERSION-bin.zip"
    
    try {
        Write-Host "[INFO] Downloading Maven $MAVEN_VERSION..." -ForegroundColor Gray
        Invoke-WebRequest -Uri $permUrl -OutFile $zip -ErrorAction Stop
        
        Write-Host "[INFO] Extracting..." -ForegroundColor Gray
        Expand-Archive -Path $zip -DestinationPath $MAVEN_ROOT -Force
        Remove-Item $zip
    }
    catch {
        Write-Host "[ERROR] Download or Extraction Failed: $_" -ForegroundColor Red
        exit
    }
}
else {
    Write-Host "[OK] Maven binaries physically verified." -ForegroundColor Green
}

# 4. ENVIRONMENT PATH
Write-Host "`n--- STEP 3: System PATH ---" -ForegroundColor Magenta
$machinePath = [System.Environment]::GetEnvironmentVariable("Path", "Machine")

if ($machinePath -notlike "*$MAVEN_BIN*") {
    Write-Host "[INFO] Updating Machine Registry..." -ForegroundColor Green
    
    # Safe concatenation without string interpolation
    $newPath = $machinePath
    if (-not $newPath.EndsWith(";")) {
        $newPath += ";"
    }
    $newPath += $MAVEN_BIN
    
    [System.Environment]::SetEnvironmentVariable("Path", $newPath, "Machine")
}
else {
    Write-Host "[OK] PATH registry already configured." -ForegroundColor Green
}

# 5. VERIFICATION
Write-Host "`n--- STEP 4: Verification ---" -ForegroundColor Magenta
if (Test-Path $mvnCmd) {
    Write-Host "[SUCCESS] mvn.cmd is fully installed at $mvnCmd" -ForegroundColor Green
    Write-Host "`nCRITICAL RESTART INSTRUCTIONS:" -ForegroundColor Yellow
    Write-Host "1. Close THIS blue window."
    Write-Host "2. Close your ENTIRE terminal application."
    Write-Host "3. Reopen your terminal and type 'mvn -v'."
}
else {
    Write-Host "[FAIL] Installation did not complete." -ForegroundColor Red
}
