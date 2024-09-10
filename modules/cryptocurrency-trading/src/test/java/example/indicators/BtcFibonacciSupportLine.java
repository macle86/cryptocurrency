package example.indicators;

import com.seomse.commons.utils.time.YmdUtil;
import io.runon.cryptocurrency.trading.CryptocurrencySymbolCandle;
import io.runon.trading.TradingTimes;
import io.runon.trading.data.csv.CsvCandle;
import io.runon.trading.technical.analysis.candle.TradeCandle;
import io.runon.trading.technical.analysis.indicators.fibonacci.Fibonacci;
import io.runon.trading.technical.analysis.indicators.fibonacci.FibonacciData;
import io.runon.trading.view.TradingChart;

import java.time.ZoneId;

/**
 * @author macle
 */
public class BtcFibonacciSupportLine {

    public static void main(String[] args) {
        String symbol = "BTCBUSD";
        String interval = "1d";

        String path = CryptocurrencySymbolCandle.SPOT_PATH + "/" + symbol + "/" + interval;
        ZoneId zoneId = TradingTimes.UTC_ZONE_ID;
        long candleTime = TradingTimes.getIntervalTime(interval);

//        YmdUtil.getTime("20180101", zoneId), YmdUtil.getTime("20220922", zoneId
        long startTime = YmdUtil.getTime("20180101", zoneId);
        long endTime = YmdUtil.getTime("20220924", zoneId);

        TradeCandle[] candles = CsvCandle.load(path, candleTime, startTime, endTime, zoneId);

        // 연속 검색 범위
        int n = 5;
        FibonacciData[] dataArray = Fibonacci.supportLines(candles, candles.length,10, 5000);

        TradingChart chart = new TradingChart(candles, 1700, 1000, TradingChart.ChartDateType.DAY);
        chart.addVolume(candles);

        chart.addLine(Fibonacci.get1000Array(dataArray), "black", 1);
        chart.addLine(Fibonacci.get764Array(dataArray), "#003366", 1);
        chart.addLine(Fibonacci.get618Array(dataArray), "#003366", 1);
        chart.addLine(Fibonacci.get500Array(dataArray), "#003399", 1);
        chart.addLine(Fibonacci.get382Array(dataArray), "#0033FF", 1);
        chart.addLine(Fibonacci.get236Array(dataArray), "#0066FF", 1);
        chart.addLine(Fibonacci.get000Array(dataArray), "#0099FF", 1);
        chart.view();
    }
}
