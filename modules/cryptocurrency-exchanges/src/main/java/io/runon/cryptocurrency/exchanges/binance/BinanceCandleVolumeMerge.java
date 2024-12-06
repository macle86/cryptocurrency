package io.runon.cryptocurrency.exchanges.binance;

import io.runon.cryptocurrency.trading.CandleVolumeMerge;
import io.runon.cryptocurrency.trading.CandleVolumeMergerStore;
import io.runon.cryptocurrency.trading.CandleVolumeMergerStoreList;
import io.runon.cryptocurrency.trading.CryptocurrencyDataPath;

/**
 * 바이낸스 캔들 데이터
 * 거래량 결합
 * @author macle
 */
public class BinanceCandleVolumeMerge {


    public static CandleVolumeMergerStore newMergeStore(String interval, long range) {
        CandleVolumeMergerStore candleVolumeMergerStore = new CandleVolumeMergerStore(newCandleVolumeMerge(interval));
        candleVolumeMergerStore.setRange(range);
        return candleVolumeMergerStore;
    }

    public static CandleVolumeMergerStore newMergeStore(String interval, int range) {
        CandleVolumeMergerStore candleVolumeMergerStore = new CandleVolumeMergerStore(newCandleVolumeMerge(interval));
        candleVolumeMergerStore.setRange(range);
        return candleVolumeMergerStore;
    }

    public static CandleVolumeMergerStoreList newMergeStoreList(String interval, long range) {
        CandleVolumeMergerStoreList candleVolumeMergerStore = new CandleVolumeMergerStoreList(newCandleVolumeMerge(interval));
        candleVolumeMergerStore.setRange(range);
        return candleVolumeMergerStore;
    }


    public static CandleVolumeMergerStoreList newMergeStoreList(String interval, int range) {
        CandleVolumeMergerStoreList candleVolumeMergerStore = new CandleVolumeMergerStoreList(newCandleVolumeMerge(interval));
        candleVolumeMergerStore.setRange(range);
        return candleVolumeMergerStore;
    }

    public static CandleVolumeMerge newCandleVolumeMerge(String interval) {
        CandleVolumeMerge candleVolumeMerge = new CandleVolumeMerge();
        String path = CryptocurrencyDataPath.getSpotCandleDirPath() + "/BTCBUSD";
        String[] addPaths = new String[]{CryptocurrencyDataPath.getSpotCandleDirPath() + "/BTCUSDT", CryptocurrencyDataPath.getFuturesCandleDirPath() + "/BTCUSDT", CryptocurrencyDataPath.getFuturesCandleDirPath() + "/BTCBUSD"};
        candleVolumeMerge.setPath(path);
        candleVolumeMerge.setAddPaths(addPaths);
        candleVolumeMerge.setInterval(interval);
        return candleVolumeMerge;
    }
}
