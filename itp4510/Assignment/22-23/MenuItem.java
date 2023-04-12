public class MenuItem {
    private String food;


    public MenuItem(String food){
        this.food = food;
    }

    public String getMenuItemFood(){
        return this.food;
    }

    public String toString(){
        return food;
    }
}
