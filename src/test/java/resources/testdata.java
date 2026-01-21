package resources;

import pojo.addplace;
import pojo.locations;

import java.util.ArrayList;

public class testdata {
    addplace p=new addplace();

    public addplace addplace(String name,String language,String addess){
        p.setAccuracy(100);
        p.setAddress(addess);
        p.setLanguage(language);
        p.setName(name);
        p.setPhone_number(11);
        p.setWebsite("https://google.com");

        locations l=new locations();
        l.setLat(20);
        l.setLng(30);
        p.setLocation(l);

        ArrayList<String> t=new ArrayList<String>();
        t.add("car");
        t.add("bus");
        p.setTypes(t);
            return p;
    }
    public String deleteplace(String placeid){
        return"{ \"place_id\": \"" + placeid + "\" }";
    }
}
