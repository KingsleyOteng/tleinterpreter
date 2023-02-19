## SPACE TARGET TRACKER/CATALOGUER

Where supplied with NORAD Two-Line-Element (TLE) files and the GPS location of a sensor the Java code tracks the orbit of space targets from the earth. This therefore is an orbit tracking tool. This tool was initially developed during my tenure at the Australian National Universitiy's in the  Research School of Astrophysics and Astronomy. Last update to this tool was in Feburary of 2023. 


![alt text](https://github.com/KingsleyOteng/tleinterpreter/blob/main/screenshot.png?raw=true)

## Overview of code

Current TLE formats are specified by [NORAD] with input from [NASA].  A derivation of the Two-Line Element set format called the Three-Line Element set exists where the first line contains the name of the satellite. These element sets are general perturbation mean elements constructed by a least squares estimation from observations. The Air Force provides the observations taken from the Space Surveillance Network, and FreeFlyer provides the capability of simulating observations to generate TLEs. The TLE elements may be propogated using an [SGP4] model (or one of the SGP8, [SDP4], SDP8 models). More,  detailed information is available at [NASA Human Space Flight](http://spaceflight.nasa.gov/realdata/sightings/SSapplications/Post/JavaSSOP/SSOP_Help/tle_def.html):**

<h3 align="center">
  <a href="http://spaceflight.nasa.gov/realdata/sightings/SSapplications/Post/JavaSSOP/SSOP_Help/tle_def.html">
    <img alt="Definition of Two-line Element Set Coordinate System" src="http://spaceflight.nasa.gov/realdata/sightings/SSapplications/Post/JavaSSOP/SSOP_Help/2line.gif">
  </a>
</h3>


## Usage from URL

```c++

orbital   
  *orb            = new orbital();
utilities 
  *util           = new utilities();

orb -> 
  SetURLData   ("https://celestrak.com/satcat/tle.php?CATNR=43108");
orb -> 
  getURLData   ();

```

## Usage from data file

```c++

orb -> 
  SetDataFile  ("Object43108.txt");
orb -> 
  getURLData   ();

```

### Parsing a single set

```c++

cout << orb -> getTLEname();
cout << orb -> getTLElineone();
cout << orb -> getTLElinetwo();

```

### Generating a TLE summary
```c++

cout << orb -> getTLEsummary();

```

```c++

TLE {
   name:  'BEIDOU-3 M',
   number:  4310,
   class:  U,
   id:  18003B,
   date:  20,
   fdmm:  -6e-07,
   sdmm:  00000-0,
   drag:  00000-0,
   ephemeris:  0,
   esn:  4310,
   inclination:  55.3263,
   ascension:  21.9999,
   eccentricity:  4719,
   perigee:  254.162,
   anomaly:  302.897,
   motion:  1.86233,
   revolution:  1415
}

```

### Parsing a stream

```c++

var parser   =  new TLE.Parser            ( options ) 
var parser   =      TLE.createParser      ( options )

````

```c++
fs.createReadStream( FILEPATH )
  .pipe( new TLE.Parser() )
  .on( 'data', function( tle ) {
    // ...
  })
```

## Creating a TLE instance from existing data

Currently does not allow the creation of TLEs. 

## Speed

It currently only handles a single TLE

```
tle_example/norad_model.cpp
Parser: 
Parser: 
```
