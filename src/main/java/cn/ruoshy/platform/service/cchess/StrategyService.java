package cn.ruoshy.platform.service.cchess;

public interface StrategyService {

    boolean verify();

    boolean execute();
    // 车
    boolean chariotMove(int id);

    // 炮
    boolean cannonMove(int id);

    // 马
    boolean horseMove(int id);

    // 兵(卒)
    boolean pawnMove(int id);

    // 将(帅)
    boolean kingMove(int id);

    // 士(仕)
    boolean adviserMove(int id);

    // 象(相)
    boolean elephantMove(int id);

}
