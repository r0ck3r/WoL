# WoL - Simple WakeOnLan application

This is simple application with GUI, only thing it does - sending magic packet to wake your PC, if it supports Wake-On-Lan technology

# Compiling
git clone http://github.com/r0ck3r/WoL.git

cd WoL

mkdir out

javac -sourcepath src/ src/simplewolapp/Main.java -d out

jar -cfe wol.jar simplewolapp/Main -C ./out/ .

# Using
java -jar <path_to_generated_wol.jar>
