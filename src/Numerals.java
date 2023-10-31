public enum Numerals {
    i0(1,"I"), i1(2,"II"), i2(3, "III"), i3(4,"IV"), i4(5,"V"), i5(6,"VI"), i6(7,"VII"), i7(8,"VIII"), i8(9,"IX"), i9(10,"X");
    private int arabic;
    private String roman;
    Numerals(int arabic, String roman) {
        this.arabic = arabic;
        this.roman = roman;
    }
    public int getArabic() {
        return arabic;
    }
    public String getRoman() {
        return roman;
    }
}
