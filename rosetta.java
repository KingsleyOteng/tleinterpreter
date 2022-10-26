/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * rosetta
 * @author kingsley oteng-amoako
 */
public class rosetta {
    
    private static String current_season;
    private static String current_conjunctions;
    private static String current_season_events;
    private static String current_season_lingo;
    private static String current_daily_twilights;
    private static String current_nodes;
    private static String current_target;
    
    private static double earth_to_sun = 148790000.00;
    private static double earth_to_moon = 384400.00;
    private static double earth_to_mercury = 0.0;
    private static double earth_to_jupiter = 0.0;
    private static double earth_to_venus = 0.0;
    private static double earth_to_saturn = 0.0;
    private static double earth_to_uranus = 0.0;
    private static double earth_to_neptune  = 0.0;
    private static double earth_to_pluto = 0.0;
    private static double radius_of_earth = 6371.00;
    private static double radius_of_moon = 1737.40;
    
    String 
     SEASON[], 
     SEASON_EVENTS[],
     SEASON_LINGO[],
     DAILY_TWILIGHTS[],
     NODES[],
     TARGET[],
     CONJUNCTIONS[];
   
    public rosetta() 
    {
        this.NODES = new String[]{"Ascending", "Descending"};
        this.CONJUNCTIONS = new String[]{"Opposition", "Conjunction"};
        this.DAILY_TWILIGHTS = new String[]{"DAY", "CIVIL TWILIGHT", "NAUTICAL TWILIGHT", "ASTRONOMICAL TWILIGHT", "NIGHT"};
        this.SEASON_EVENTS = new String[]{"Winter Solstice","Autumn Equinox","Summer Solstice","Vernal Equinox"};
        this.SEASON_LINGO = new String[]{"Dec Solstice","Sept Equinox","June Solstice","Mar Equinox"};
        this.SEASON = new String[]{"Winter", "Fall", "Summer", "Spring"};
    }   

   private void set_target(int target)
    {
        rosetta.current_target = this.TARGET[target];
    };
   
   private void set_nodes(int nodes)
    {
        rosetta.current_nodes = this.NODES[nodes];
    };
   
   private void set_conjunctions(int conjunctions)
    {
        rosetta.current_conjunctions = this.CONJUNCTIONS[conjunctions];
    };
   
   private void set_daily_twilights(int daily_twilights)
    {
        rosetta.current_daily_twilights = this.DAILY_TWILIGHTS[daily_twilights];
    };
   
   private void set_season_events(int season_events)
    {
        rosetta.current_season_events = this.SEASON_EVENTS[season_events];
    };
   
   private void set_current_conjunctions(int current_conjunctions)
    {
        rosetta.current_conjunctions = this.CONJUNCTIONS[current_conjunctions];
    };
   
   private void set_current_season(int current_season)
    {
        rosetta.current_season = this.SEASON[current_season];
    };
   
   private String get_target(int target)
   {
       return this.current_target;
   }
   private String get_nodes(int node)
    {
        return this.NODES[node];
    };
   
   private String get_conjunctions(int conjunction)
    {
        return this.CONJUNCTIONS[conjunction];
    };
   
   private String get_daily_twilights(int daily)
    {
        return this.DAILY_TWILIGHTS[daily];
    };
   
   private String get_season_events(int season)
    {
        return this.SEASON_EVENTS[season];
    };
   
   private String get_current_conjunctions(int conjunction)
    {
        return this.CONJUNCTIONS[conjunction];
    };
   
   private String get_current_season(int season)
    {
        return this.SEASON[season];
    };
}
    
     
    

