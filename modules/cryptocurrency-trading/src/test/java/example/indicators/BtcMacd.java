package example.indicators;

import com.seomse.commons.utils.time.YmdUtil;

import io.runon.cryptocurrency.trading.CryptocurrencySymbolCandle;
import io.runon.trading.TradingTimes;
import io.runon.trading.data.csv.CsvCandle;
import io.runon.trading.technical.analysis.candle.TradeCandle;
import io.runon.trading.technical.analysis.indicators.ma.Macd;
import io.runon.trading.technical.analysis.indicators.ma.MacdData;
import io.runon.trading.view.TradingChart;

import java.time.ZoneId;

/**
 * @author macle
 */
public class BtcMacd {
    public static void main(String[] args) {

        String symbol = "BTCUSDT";
        String interval = "1d";

        String path = CryptocurrencySymbolCandle.FUTURES_PATH + "/" + symbol + "/" + interval;
        ZoneId zoneId = TradingTimes.UTC_ZONE_ID;
        long candleTime = TradingTimes.getIntervalTime(interval);

        long startTime = YmdUtil.getTime("20180101", zoneId);
        long endTime = System.currentTimeMillis();

        TradeCandle[] candles = CsvCandle.load(path, candleTime, startTime, endTime);
        MacdData[] dataArray = Macd.get(candles);

        TradingChart chart = new TradingChart(candles, 1700, 1000, TradingChart.ChartDateType.DAY);
        chart.addVolume(candles);

        chart.addLine(dataArray, "black", 1, false);
        chart.addLine(Macd.getSignals(dataArray), "blue", 1, false);
        chart.addLine(Macd.getOscillators(dataArray), "red", 1, false);
        chart.view();
    }
}
