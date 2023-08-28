package student.examples.domain;

public class Currency {
    
    private String charCode;
    private int numCode;
    private float ratio;

    public Currency(String charCode) {
        this.charCode = charCode;
    }

    public Currency(String charCode, int numCode, float ratio) {
        this.charCode = charCode;
        this.numCode = numCode;
        this.ratio = ratio;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNumCode() {
        return numCode;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        // String toString = "Currency [" + charCode + ", numCode=" + numCode + ", ratio=" + ratio + "]";
        // if (numCode == 0) {
        //     toString = charCode;
        // }
        return charCode;
    }

}
