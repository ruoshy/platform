package cn.ruoshy.platform.eum.cchess;

public enum Piece {

    HONGSHUAI("帅", 1),
    HONGJU1("车1", 2),
    HONGJU2("车2", 3),
    HONGPAO1("炮1", 4),
    HONGPAO2("炮2", 5),
    HONGMA1("马1", 6),
    HONGMA2("马2", 7),
    HONGXIANG1("相1", 8),
    HONGXIANG2("相2", 9),
    HONGSHI1("仕1", 10),
    HONGSHI2("仕2", 11),
    HONGBING1("兵1", 12),
    HONGBING2("兵2", 13),
    HONGBING3("兵3", 14),
    HONGBING4("兵4", 15),
    HONGBING5("兵5", 16),
    //
    HEIJIANG("将", 21),
    HEIJU1("车1", 22),
    HEIJU2("车2", 23),
    HEIPAO1("炮1", 24),
    HEIPAO2("炮2", 25),
    HEIMA1("马1", 26),
    HEIMA2("马2", 27),
    HEIXIANG1("象1", 28),
    HEIXIANG2("象2", 29),
    HEISHI1("士1", 30),
    HEISHI2("士2", 31),
    HEIBING1("卒1", 32),
    HEIBING2("卒2", 33),
    HEIBING3("卒3", 34),
    HEIBING4("卒4", 35),
    HEIBING5("卒5", 36);

    private String name;
    private int id;

    Piece(String name, int id) {
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
