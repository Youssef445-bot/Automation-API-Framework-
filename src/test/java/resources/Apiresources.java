package resources;

public enum Apiresources {

    addplaceapi("/maps/api/place/add/json"),
    getplaceapi("/maps/api/place/get/json"),
    deleteplaceapi("/maps/api/place/delete/json");
    private final String resources;

    Apiresources(String resources) {
        this.resources = resources;
    }

    public String getResources() {
        return resources;
    }

}
