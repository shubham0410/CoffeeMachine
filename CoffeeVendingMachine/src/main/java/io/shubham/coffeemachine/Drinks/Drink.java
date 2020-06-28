package io.shubham.coffeemachine.Drinks;

public abstract class Drink {
    private int hotWaterQuant;
    private int hotMilkQuant;
    private int gingerSyrupQuant;
    private int sugarSyrupQuant;
    private int teaLeavesSyrupQuant;

    //GETTERS AND SETTERS
    public int getHotWaterQuant() {
        return hotWaterQuant;
    }

    public int getHotMilkQuant() {
        return hotMilkQuant;
    }

    public int getGingerSyrupQuant() {
        return gingerSyrupQuant;
    }

    public int getSugarSyrupQuant() {
        return sugarSyrupQuant;
    }

    public int getTeaLeavesSyrupQuant() {
        return teaLeavesSyrupQuant;
    }

    public void setHotWaterQuant(int hotWaterQuant) {
        this.hotWaterQuant = hotWaterQuant;
    }

    public void setHotMilkQuant(int hotMilkQuant) {
        this.hotMilkQuant = hotMilkQuant;
    }

    public void setGingerSyrupQuant(int gingerSyrupQuant) {
        this.gingerSyrupQuant = gingerSyrupQuant;
    }

    public void setSugarSyrupQuant(int sugarSyrupQuant) {
        this.sugarSyrupQuant = sugarSyrupQuant;
    }

    public void setTeaLeavesSyrupQuant(int teaLeavesSyrupQuant) {
        this.teaLeavesSyrupQuant = teaLeavesSyrupQuant;
    }

}
