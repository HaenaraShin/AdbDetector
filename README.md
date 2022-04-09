# ADB Detector

![ADB Detector title](adbdetector_title.png)

**🕵️ An android library to detect USB Debugging connection.**

📌 자세한 설명은 [블로그](https://haenarashin.github.io/kotlin/2021/12/05/adb_detect.html)에서 확인하실 수 있습니다.

# Introduce

ADB Detector can check USB Debugging is activated both dynamically and statically.
 
To check it dynamically, use a register a BroadcastReceiver.
 
To check it statically, use checkUsbDebuggingMode function.

**⚠️ADB Detector cannot check ADB with WiFi.⚠️**

# Sample Run

|![Sample run](sample_run.gif)|![Sample run2](sample_run2.gif)|
|---|---|


Sample app shows how ADB Detector works.

# Licences

```
MIT License

Copyright (c) 2020 Haenala Shin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
