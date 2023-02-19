## SPACE TARGET TRACKER/CATALOGUER

Where supplied with NORAD Two-Line-Element (TLE) files and the GPS location of a sensor the Java code tracks the orbit of space targets from the earth. This therefore is an orbit tracking tool. This tool was initially developed during my tenure at the Australian National Universitiy's in the  Research School of Astrophysics and Astronomy. Last update to this tool was in Feburary of 2023. 


![alt text](https://github.com/KingsleyOteng/tleinterpreter/blob/main/screenshot.png?raw=true)

** FORMAT **

Current TLE formats are specified by [NORAD] with input from [NASA].  A derivation of the Two-Line Element set format called the Three-Line Element set exists where the first line contains the name of the satellite. These element sets are general perturbation mean elements constructed by a least squares estimation from observations. The Air Force provides the observations taken from the Space Surveillance Network, and FreeFlyer provides the capability of simulating observations to generate TLEs. The TLE elements may be propogated using an [SGP4] model (or one of the SGP8, [SDP4], SDP8 models).
