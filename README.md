# WoL - Simple WakeOnLan application

This is a simple GUI application. The only thing it does is send a magic packet to wake your PC up if it supports Wake-On-Lan

# Building
```
mkdir out

javac -sourcepath src/ src/simplewolapp/Main.java -d out

jar -cfe wol.jar simplewolapp/Main -C ./out/ .
```
# Using
```
java -jar <path_to_generated_wol.jar>
```
